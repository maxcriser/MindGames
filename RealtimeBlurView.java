package com.lgi.horizon.ui.realtimeblur;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;

import com.lgi.horizon.ui.BuildConfig;
import com.lgi.horizon.ui.R;

import by.istin.android.xcore.utils.Log;

public class RealtimeBlurView extends View {

    private static final int MAX_RADIUS = 25;
    private static final String TAG = RealtimeBlurView.class.getSimpleName();
    private float mDownsampleFactor;
    private int mOverlayColor;
    private float mBlurRadius;

    private boolean mDirty;
    private Bitmap mBitmapToBlur;
    private Bitmap mBlurredBitmap;
    private Canvas mBlurringCanvas;
    private RenderScript mRenderScript;
    private ScriptIntrinsicBlur mBlurScript;
    private Allocation mBlurInput, mBlurOutput;
    private boolean mIsRendering;
    private final Rect mRectSrc = new Rect();
    private final Rect mRectDst = new Rect();
    // mDecorView should be the root view of the activity (even if you are on a different window like a dialog)
    private View mDecorView;
    // If the view is on different root view (usually means we are on a PopupWindow),
    // we need to manually call invalidate() in onPreDraw(), otherwise we will not be able to see the changes
    private boolean mDifferentRoot;
    private int mRenderingCount;

    public RealtimeBlurView(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RealtimeBlurView);

        try {
            mBlurRadius = a.getDimension(R.styleable.RealtimeBlurView_realtimeBlurRadius,
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics()));
            mDownsampleFactor = a.getFloat(R.styleable.RealtimeBlurView_realtimeDownsampleFactor, 4);
            mOverlayColor = a.getColor(R.styleable.RealtimeBlurView_realtimeOverlayColor, ContextCompat.getColor(getContext(), R.color.DarknessOpacity80));
        } finally {
            a.recycle();
        }
    }

    public void setBlurRadius(final float radius) {
        if (mBlurRadius != radius) {
            mBlurRadius = radius;
            mDirty = true;
            invalidate();
        }
    }

    public void setDownsampleFactor(final float factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Downsample factor must be greater than 0.");
        }

        if (mDownsampleFactor != factor) {
            mDownsampleFactor = factor;
            mDirty = true; // may also change blur radius
            releaseBitmap();
            invalidate();
        }
    }

    public void setOverlayColor(final int color) {
        if (mOverlayColor != color) {
            mOverlayColor = color;
            invalidate();
        }
    }

    private void releaseBitmap() {
        if (mBlurInput != null) {
            mBlurInput.destroy();
            mBlurInput = null;
        }
        if (mBlurOutput != null) {
            mBlurOutput.destroy();
            mBlurOutput = null;
        }
        if (mBitmapToBlur != null) {
            mBitmapToBlur = null;
        }
        if (mBlurredBitmap != null) {
            mBlurredBitmap = null;
        }
    }

    private void releaseScript() {
        if (mRenderScript != null) {
            mRenderScript.destroy();
            mRenderScript = null;
        }
        if (mBlurScript != null) {
            mBlurScript.destroy();
            mBlurScript = null;
        }
    }

    protected void release() {
        releaseBitmap();
        releaseScript();
    }

    protected boolean prepare() {
        if (mBlurRadius == 0) {
            release();
            return false;
        }

        float downsampleFactor = mDownsampleFactor;

        if (mDirty || mRenderScript == null) {
            if (mRenderScript == null) {
                try {
                    mRenderScript = RenderScript.create(getContext());
                    mBlurScript = ScriptIntrinsicBlur.create(mRenderScript, Element.U8_4(mRenderScript));
                } catch (final android.support.v8.renderscript.RSRuntimeException e) {
                    if (BuildConfig.DEBUG) {
                        if (e.getMessage() != null && e.getMessage().startsWith("Error loading RS jni library: java.lang.UnsatisfiedLinkError:")) {
                            throw new RuntimeException("Error loading RS jni library, Upgrade buildToolsVersion=\"24.0.2\" or higher may solve this issue");
                        } else {
                            throw e;
                        }
                    } else {
                        // In release mode, just ignore
                        releaseScript();
                        return false;
                    }
                }
            }

            mDirty = false;
            float radius = mBlurRadius / downsampleFactor;
            if (radius > MAX_RADIUS) {
                downsampleFactor = downsampleFactor * radius / MAX_RADIUS;
                radius = MAX_RADIUS;
            }
            mBlurScript.setRadius(radius);
        }

        final int width = getWidth();
        final int height = getHeight();

        final int scaledWidth = Math.max(1, (int) (width / downsampleFactor));
        final int scaledHeight = Math.max(1, (int) (height / downsampleFactor));

        if (mBlurringCanvas == null || mBlurredBitmap == null
                || mBlurredBitmap.getWidth() != scaledWidth
                || mBlurredBitmap.getHeight() != scaledHeight) {
            releaseBitmap();

            boolean r = false;
            try {
                mBitmapToBlur = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
                if (mBitmapToBlur == null) {
                    return false;
                }
                mBlurringCanvas = new Canvas(mBitmapToBlur);

                mBlurInput = Allocation.createFromBitmap(mRenderScript, mBitmapToBlur,
                        Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
                mBlurOutput = Allocation.createTyped(mRenderScript, mBlurInput.getType());

                mBlurredBitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
                if (mBlurredBitmap == null) {
                    return false;
                }

                r = true;
            } catch (final OutOfMemoryError e) {
                // Bitmap.createBitmap() may cause OOM error
                // Simply ignore and fallback
            } finally {
                if (!r) {
                    releaseBitmap();
                    return false;
                }
            }
        }
        return true;
    }

    protected void blur(final Bitmap bitmapToBlur, final Bitmap blurredBitmap) {
        mBlurInput.copyFrom(bitmapToBlur);
        mBlurScript.setInput(mBlurInput);
        mBlurScript.forEach(mBlurOutput);
        mBlurOutput.copyTo(blurredBitmap);
    }

    private final ViewTreeObserver.OnPreDrawListener preDrawListener = new ViewTreeObserver.OnPreDrawListener() {

        @Override
        public boolean onPreDraw() {
            final int[] locations = new int[2];
            Bitmap oldBmp = mBlurredBitmap;
            final View decor = mDecorView;
            if (decor != null && isShown() && prepare()) {
                final boolean redrawBitmap = mBlurredBitmap != oldBmp;
                oldBmp = null;
                decor.getLocationOnScreen(locations);
                int x = -locations[0];
                int y = -locations[1];

                getLocationOnScreen(locations);
                x += locations[0];
                y += locations[1];

                // just erase transparent
                mBitmapToBlur.eraseColor(mOverlayColor & ContextCompat.getColor(getContext(), R.color.White));

                final int rc = mBlurringCanvas.save();
                mIsRendering = true;
                mRenderingCount++;
                try {
                    mBlurringCanvas.scale(1.f * mBitmapToBlur.getWidth() / getWidth(), 1.f * mBitmapToBlur.getHeight() / getHeight());
                    mBlurringCanvas.translate(-x, -y);
                    if (decor.getBackground() != null) {
                        decor.getBackground().draw(mBlurringCanvas);
                    }
                    decor.draw(mBlurringCanvas);
                } catch (final RuntimeException e) {
                    Log.xe(TAG, "onPreDraw: " + e);
                } finally {
                    mIsRendering = false;
                    mRenderingCount--;
                    mBlurringCanvas.restoreToCount(rc);
                }

                blur(mBitmapToBlur, mBlurredBitmap);

                if (redrawBitmap || mDifferentRoot) {
                    invalidate();
                }
            }

            return true;
        }
    };

    protected View getActivityDecorView() {
        Context ctx = getContext();
        for (int i = 0; i < 4 && ctx != null && !(ctx instanceof Activity) && ctx instanceof ContextWrapper; i++) {
            ctx = ((ContextWrapper) ctx).getBaseContext();
        }
        if (ctx instanceof Activity) {
            return ((Activity) ctx).getWindow().getDecorView();
        } else {
            return null;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mDecorView = getActivityDecorView();
        if (mDecorView != null) {
            mDecorView.getViewTreeObserver().addOnPreDrawListener(preDrawListener);
            mDifferentRoot = mDecorView.getRootView() != getRootView();
            if (mDifferentRoot) {
                mDecorView.postInvalidate();
            }
        } else {
            mDifferentRoot = false;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mDecorView != null) {
            mDecorView.getViewTreeObserver().removeOnPreDrawListener(preDrawListener);
        }
        release();
        super.onDetachedFromWindow();
    }

    @Override
    public void draw(final Canvas canvas) {
        if (mIsRendering) {
            // Quit here, don't draw views above me
            throw new RuntimeException();
        } else if (mRenderingCount > 0) {
            // Doesn't support blurview overlap on another blurview
        } else {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        drawBlurredBitmap(canvas, mBlurredBitmap, mOverlayColor);
    }

    /**
     * Custom draw the blurred bitmap and color to define your own shape
     *
     * @param canvas
     * @param blurredBitmap
     * @param overlayColor
     */
    protected void drawBlurredBitmap(final Canvas canvas, final Bitmap blurredBitmap, final int overlayColor) {
        if (blurredBitmap != null && !blurredBitmap.isRecycled()) {
            mRectSrc.right = blurredBitmap.getWidth();
            mRectSrc.bottom = blurredBitmap.getHeight();
            mRectDst.right = getWidth();
            mRectDst.bottom = getHeight();
            canvas.drawBitmap(blurredBitmap, mRectSrc, mRectDst, null);
        }
        canvas.drawColor(overlayColor);
    }

    static {
        try {
            RealtimeBlurView.class.getClassLoader().loadClass("android.support.v8.renderscript.RenderScript");
        } catch (final ClassNotFoundException e) {
            throw new RuntimeException("RenderScript support not enabled. Add \"android { defaultConfig { renderscriptSupportModeEnabled true }}\" in your build.gradle");
        }
    }
}
