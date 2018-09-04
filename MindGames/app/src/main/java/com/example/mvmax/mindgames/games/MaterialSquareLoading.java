package com.example.mvmax.mindgames.games;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.example.mvmax.mindgames.R;

public class MaterialSquareLoading extends RelativeLayout {

  private static final int DEFAULT_OUTER_COLOR = Color.parseColor("#1A237E");
  private static final int DEFAULT_INNER_COLOR = Color.parseColor("#01579B");
  public static final int DEFAULT_DURATION_ROTATION_INNER = 4862;
  public static final int DEFAULT_DURATION_ROTATION_OUTER = 6028;

  private CardView innerSquare, outerSquare;
  private int outerSize;
  private int innerSize;
  private boolean sizeChanged;
  private boolean requestStartAnimation;
  private int outerRotationDuration;
  private int innerRotationDuration;

  public MaterialSquareLoading(final Context context) {
    super(context);
    init(context, null);
  }

  public MaterialSquareLoading(final Context context, final AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public MaterialSquareLoading(final Context context, final AttributeSet attrs, final int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(final Context context, final AttributeSet attrs) {
    inflateSelf();
    setUpAttrs(attrs);

    if (!isInEditMode()) {
      setVisibility(INVISIBLE);
    }
  }

  private void setUpAttrs(final AttributeSet attrs) {
    int innerColor = DEFAULT_INNER_COLOR;
    int outerColor = DEFAULT_OUTER_COLOR;
    float innerRadius = innerSquare.getRadius();
    float outerRadius = outerSquare.getRadius();
    outerRotationDuration = DEFAULT_DURATION_ROTATION_OUTER;
    innerRotationDuration = DEFAULT_DURATION_ROTATION_INNER;
    if (attrs != null) {
      final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MaterialSquareLoading);
      innerColor = a.getColor(R.styleable.MaterialSquareLoading_innerColor, DEFAULT_INNER_COLOR);
      outerColor = a.getColor(R.styleable.MaterialSquareLoading_outerColor, DEFAULT_OUTER_COLOR);
      innerRadius = a.getDimension(R.styleable.MaterialSquareLoading_innerRadius, innerSquare.getRadius());
      outerRadius = a.getDimension(R.styleable.MaterialSquareLoading_outerRadius, outerSquare.getRadius());
      innerRotationDuration = a.getInt(R.styleable.MaterialSquareLoading_rotationInnerDuration, DEFAULT_DURATION_ROTATION_INNER);
      outerRotationDuration = a.getInt(R.styleable.MaterialSquareLoading_rotationOuterDuration, DEFAULT_DURATION_ROTATION_OUTER);
      a.recycle();
    }

    innerSquare.setCardBackgroundColor(innerColor);
    outerSquare.setCardBackgroundColor(outerColor);

    innerSquare.setRadius(innerRadius);
    outerSquare.setRadius(outerRadius);
  }

  private void inflateSelf() {
    inflate(getContext(), R.layout.material_square_loading_layout, this);

    outerSquare = (CardView) findViewById(R.id.material_square1);
    innerSquare = (CardView) findViewById(R.id.material_square2);
  }

  @Override protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    setUpSquareSize(Math.min(h, w));
    sizeChanged = true;

    if (requestStartAnimation) {
      show();
    }
  }

  private void setUpSquareSize(final int size) {
    final double hypotenuse = Math.sqrt(2 * size * size);
    final int realSize = size - (int) (hypotenuse - size);

    final LayoutParams outerParams = (LayoutParams) outerSquare.getLayoutParams();
    outerSize = outerParams.width = realSize;
    outerParams.height = realSize;

    final LayoutParams innerParams = (LayoutParams) innerSquare.getLayoutParams();
    innerSize = innerParams.width = realSize / 2;
    innerParams.height = realSize / 2;
  }

  @Override protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
  }

  public void show() {
    if (!sizeChanged) {
      requestStartAnimation = true;
    } else {
      startGlobalAnimation();
    }
  }

  public void hide() {
    endGlobalAnimation();
  }

  private void endGlobalAnimation() {
    cancelViewTagAnimator(this);
    // SCALE ENTER
    final ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1, 0);
    scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(final ValueAnimator animation) {
        final Float scale = (Float) animation.getAnimatedValue();
        setScaleX(scale);
        setScaleY(scale);
      }
    });

    scaleAnimator.setDuration(400);
    scaleAnimator.setInterpolator(new AccelerateInterpolator());

    scaleAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(final Animator animation) {
        super.onAnimationEnd(animation);
        cancelViewTagAnimator(outerSquare);
        cancelViewTagAnimator(innerSquare);
        setVisibility(INVISIBLE);
      }
    });

    scaleAnimator.start();
    setTag(scaleAnimator);
  }

  private void startGlobalAnimation() {

    // OUTER SQUARE
    final ValueAnimator outerAnimator = createCosinusValueAnimator(outerSize, outerSize * 0.8f, new CosinusAnimatorUpdateListener() {
      @Override public void onCosinusAnimatorUpdate(final float value) {
        final LayoutParams params = (LayoutParams) outerSquare.getLayoutParams();
        params.width = (int) value;
        params.height = (int) value;
        outerSquare.requestLayout();
      }
    });

    outerAnimator.setDuration(2000);
    outerAnimator.setInterpolator(new LinearInterpolator());
    outerAnimator.setRepeatCount(ValueAnimator.INFINITE);

    final ValueAnimator outerRotateAnimator = ObjectAnimator.ofFloat(outerSquare, View.ROTATION, 0, 360);
    outerRotateAnimator.setInterpolator(new LinearInterpolator());
    outerRotateAnimator.setDuration(outerRotationDuration);
    outerRotateAnimator.setRepeatCount(ValueAnimator.INFINITE);

    final AnimatorSet outerAnimatorSet = new AnimatorSet();
    outerAnimatorSet.playTogether(outerRotateAnimator, outerAnimator);

    outerSquare.setTag(outerAnimatorSet);
    outerAnimatorSet.start();

    // INNER SQUARE
    final ValueAnimator innerAnimator = createCosinusValueAnimator(innerSize * 0.8f, innerSize, new CosinusAnimatorUpdateListener() {
      @Override public void onCosinusAnimatorUpdate(final float value) {
        final LayoutParams params = (LayoutParams) innerSquare.getLayoutParams();
        params.width = (int) value;
        params.height = (int) value;
        innerSquare.requestLayout();
      }
    });

    innerAnimator.setDuration(2000);
    innerAnimator.setInterpolator(new LinearInterpolator());
    innerAnimator.setRepeatCount(ValueAnimator.INFINITE);

    final ValueAnimator innerRotateAnimator = ObjectAnimator.ofFloat(innerSquare, View.ROTATION, 0, -360);
    innerRotateAnimator.setInterpolator(new LinearInterpolator());
    innerRotateAnimator.setDuration(innerRotationDuration);
    innerRotateAnimator.setRepeatCount(ValueAnimator.INFINITE);

    final AnimatorSet innerAnimatorSet = new AnimatorSet();
    innerAnimatorSet.playTogether(innerRotateAnimator, innerAnimator);

    innerSquare.setTag(innerAnimatorSet);
    innerAnimatorSet.start();

    // SCALE ENTER
    final ValueAnimator scaleAnimator = ValueAnimator.ofFloat(0, 1);
    scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(final ValueAnimator animation) {
        final Float offset = (Float) animation.getAnimatedValue();
        setScaleX(offset);
        setScaleY(offset);
        setRotation(-(1 - offset) * 50);
      }
    });

    scaleAnimator.setDuration(400);
    scaleAnimator.setInterpolator(new DecelerateInterpolator());

    scaleAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationStart(final Animator animation) {
        super.onAnimationStart(animation);
        setVisibility(VISIBLE);
      }
    });

    scaleAnimator.start();

    setTag(scaleAnimator);
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    cancelViewTagAnimator(outerSquare);
    cancelViewTagAnimator(innerSquare);
    cancelViewTagAnimator(this);
  }

  public static ValueAnimator createCosinusValueAnimator(final float start, final float end, final CosinusAnimatorUpdateListener listener) {
    final ValueAnimator valueAnimator = ValueAnimator.ofFloat((float) (-Math.PI), (float) (Math.PI));
    valueAnimator.setInterpolator(new LinearInterpolator());
    if (listener != null) {
      valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        @Override public void onAnimationUpdate(final ValueAnimator animation) {
          final double offset = (Math.cos((float) animation.getAnimatedValue()) + 1) / 2;
          final float value = (float) (start + (end - start) * offset);
          listener.onCosinusAnimatorUpdate(value);
        }
      });
    }
    return valueAnimator;
  }

  public static void cancelViewTagAnimator(final View view) {
    if (view != null && view.getTag() != null && view.getTag() instanceof Animator) {
      ((Animator) view.getTag()).cancel();
    }
  }

  public interface CosinusAnimatorUpdateListener {
    void onCosinusAnimatorUpdate(float value);
  }

  public interface AnimationListener {
    void onAnimationFinished();
  }
}
