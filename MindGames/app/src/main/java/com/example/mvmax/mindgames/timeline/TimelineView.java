package com.example.mvmax.mindgames.timeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.mvmax.mindgames.R;

public class TimelineView extends View {

    private Drawable mMarker;
    private Drawable mStartLine;
    private Drawable mEndLine;
    private int mMarkerSize;
    private int mLineSize;
    private int mLineOrientation;
    private int mLinePadding;
    private boolean mMarkerInCenter;

    private Rect mBounds;
    private final Context mContext;

    public TimelineView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(final AttributeSet attrs) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.timeline_style);
        mMarker = typedArray.getDrawable(R.styleable.timeline_style_marker);
        mStartLine = typedArray.getDrawable(R.styleable.timeline_style_line);
        mEndLine = typedArray.getDrawable(R.styleable.timeline_style_line);
        mMarkerSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_markerSize, Utils.dpToPx(20, mContext));
        mLineSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_lineSize, Utils.dpToPx(2, mContext));
        mLineOrientation = typedArray.getInt(R.styleable.timeline_style_lineOrientation, 1);
        mLinePadding = typedArray.getDimensionPixelSize(R.styleable.timeline_style_linePadding, 0);
        mMarkerInCenter = typedArray.getBoolean(R.styleable.timeline_style_markerInCenter, true);
        typedArray.recycle();

        if (mMarker == null) {
            mMarker = mContext.getResources().getDrawable(R.drawable.marker);
        }

        if (mStartLine == null && mEndLine == null) {
            mStartLine = new ColorDrawable(mContext.getResources().getColor(android.R.color.darker_gray));
            mEndLine = new ColorDrawable(mContext.getResources().getColor(android.R.color.darker_gray));
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Width measurements of the width and height and the inside view of child controls
        final int w = mMarkerSize + getPaddingLeft() + getPaddingRight();
        final int h = mMarkerSize + getPaddingTop() + getPaddingBottom();

        // Width and height to determine the final view through a systematic approach to decision-making
        final int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        final int heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);

        setMeasuredDimension(widthSize, heightSize);
        initDrawable();
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // When the view is displayed when the callback
        // Positioning Drawable coordinates, then draw
        initDrawable();
    }

    private void initDrawable() {
        final int pLeft = getPaddingLeft();
        final int pRight = getPaddingRight();
        final int pTop = getPaddingTop();
        final int pBottom = getPaddingBottom();

        final int width = getWidth();// Width of current custom view
        final int height = getHeight();

        final int cWidth = width - pLeft - pRight;// Circle width
        final int cHeight = height - pTop - pBottom;

        final int markSize = Math.min(mMarkerSize, Math.min(cWidth, cHeight));

        if (mMarkerInCenter) { //Marker in center is true

            if (mMarker != null) {
                mMarker.setBounds((width / 2) - (markSize / 2), (height / 2) - (markSize / 2), (width / 2) + (markSize / 2), (height / 2) + (markSize / 2));
                mBounds = mMarker.getBounds();
            }

        } else { //Marker in center is false

            if (mMarker != null) {
                mMarker.setBounds(pLeft, pTop, pLeft + markSize, pTop + markSize);
                mBounds = mMarker.getBounds();
            }
        }

        final int centerX = mBounds.centerX();
        final int lineLeft = centerX - (mLineSize >> 1);

        if (mLineOrientation == 0) {

            //Horizontal Line
            if (mStartLine != null) {
                mStartLine.setBounds(0, pTop + (mBounds.height() / 2), mBounds.left - mLinePadding, (mBounds.height() / 2) + pTop + mLineSize);
            }

            if (mEndLine != null) {
                mEndLine.setBounds(mBounds.right + mLinePadding, pTop + (mBounds.height() / 2), width, (mBounds.height() / 2) + pTop + mLineSize);
            }
        } else {

            //Vertical Line
            if (mStartLine != null) {
                mStartLine.setBounds(lineLeft, 0, mLineSize + lineLeft, mBounds.top - mLinePadding);
            }

            if (mEndLine != null) {
                mEndLine.setBounds(lineLeft, mBounds.bottom + mLinePadding, mLineSize + lineLeft, height);
            }
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (mMarker != null) {
            mMarker.draw(canvas);
        }

        if (mStartLine != null) {
            mStartLine.draw(canvas);
        }

        if (mEndLine != null) {
            mEndLine.draw(canvas);
        }
    }

    /**
     * Sets marker.
     *
     * @param marker will set marker drawable to timeline
     */
    public void setMarker(final Drawable marker) {
        mMarker = marker;
        initDrawable();
    }

    /**
     * Sets marker.
     *
     * @param marker will set marker drawable to timeline
     * @param color  with a color
     */
    public void setMarker(final Drawable marker, final int color) {
        mMarker = marker;
        mMarker.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        initDrawable();
    }

    /**
     * Sets marker color.
     *
     * @param color the color
     */
    public void setMarkerColor(final int color) {
        mMarker.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        initDrawable();
    }

    /**
     * Sets start line.
     *
     * @param color    the color
     * @param viewType the view type
     */
    public void setStartLine(final int color, final int viewType) {
        mStartLine = new ColorDrawable(color);
        initLine(viewType);
    }

    /**
     * Sets end line.
     *
     * @param color    the color
     * @param viewType the view type
     */
    public void setEndLine(final int color, final int viewType) {
        mEndLine = new ColorDrawable(color);
        initLine(viewType);
    }

    /**
     * Sets marker size.
     *
     * @param markerSize the marker size
     */
    public void setMarkerSize(final int markerSize) {
        mMarkerSize = markerSize;
        initDrawable();
    }

    /**
     * Sets line size.
     *
     * @param lineSize the line size
     */
    public void setLineSize(final int lineSize) {
        mLineSize = lineSize;
        initDrawable();
    }

    /**
     * Sets line padding
     *
     * @param padding the line padding
     */
    public void setLinePadding(final int padding) {
        mLinePadding = padding;
        initDrawable();
    }

    private void setStartLine(final Drawable startLine) {
        mStartLine = startLine;
        initDrawable();
    }

    private void setEndLine(final Drawable endLine) {
        mEndLine = endLine;
        initDrawable();
    }

    /**
     * Init line.
     *
     * @param viewType the view type
     */
    public void initLine(final int viewType) {
        if (viewType == LineType.BEGIN) {
            setStartLine(null);
        } else if (viewType == LineType.END) {
            setEndLine(null);
        } else if (viewType == LineType.ONLYONE) {
            setStartLine(null);
            setEndLine(null);
        }
        initDrawable();
    }

    /**
     * Gets timeline view type.
     *
     * @param position   the position
     * @param total_size the total size
     * @return the time line view type
     */
    public static int getTimeLineViewType(final int position, final int total_size) {
        if (total_size == 1) {
            return LineType.ONLYONE;
        } else if (position == 0) {
            return LineType.BEGIN;
        } else if (position == total_size - 1) {
            return LineType.END;
        } else {
            return LineType.NORMAL;
        }
    }
}