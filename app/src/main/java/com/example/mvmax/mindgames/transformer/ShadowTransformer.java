package com.example.mvmax.mindgames.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.mvmax.mindgames.adapter.ICardPagerAdapter;

public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private static final float SCALE_VALUE_INCREASED = 1.1f;
    private static final float SCALE_VALUE_DEFAULT = 1;
    private static final double SCALE_VALUE_INCREASED_MINUS_DEFAULT = 0.1;

    private final ViewPager mViewPager;
    private final ICardPagerAdapter mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled;

    public ShadowTransformer(final ViewPager pViewPager, final ICardPagerAdapter pAdapter) {
        mViewPager = pViewPager;
        pViewPager.addOnPageChangeListener(this);
        mAdapter = pAdapter;
    }

    public void disableScaling() {
        if (mScalingEnabled) {
            final CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());

            if (currentCard != null) {
                currentCard.animate().scaleY(SCALE_VALUE_DEFAULT);
                currentCard.animate().scaleX(SCALE_VALUE_DEFAULT);
            }
        }

        mScalingEnabled = false;
    }

    public void enableScaling() {
        if (!mScalingEnabled) {
            final CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());

            if (currentCard != null) {
                currentCard.animate().scaleY(SCALE_VALUE_INCREASED);
                currentCard.animate().scaleX(SCALE_VALUE_INCREASED);
            }
        }

        mScalingEnabled = true;
    }

    @Override
    public void transformPage(@NonNull final View pPage, final float pPosition) {

    }

    @Override
    public void onPageScrolled(final int pPosition, final float pPositionOffset, final int pPositionOffsetPixels) {
        final int realCurrentPosition;
        final int nextPosition;
        final float baseElevation = mAdapter.getBaseElevation();
        final float realOffset;
        final boolean goingLeft = mLastOffset > pPositionOffset;

        if (goingLeft) {
            realCurrentPosition = pPosition + 1;
            nextPosition = pPosition;
            realOffset = 1 - pPositionOffset;
        } else {
            nextPosition = pPosition + 1;
            realCurrentPosition = pPosition;
            realOffset = pPositionOffset;
        }

        if (nextPosition > mAdapter.getCount() - 1 || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        final CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);

        if (currentCard != null) {
            if (mScalingEnabled) {
                currentCard.setScaleX((float) (SCALE_VALUE_DEFAULT + SCALE_VALUE_INCREASED_MINUS_DEFAULT * (SCALE_VALUE_DEFAULT - realOffset)));
                currentCard.setScaleY((float) (SCALE_VALUE_DEFAULT + SCALE_VALUE_INCREASED_MINUS_DEFAULT * (SCALE_VALUE_DEFAULT - realOffset)));
            }
            currentCard.setCardElevation((baseElevation + baseElevation * (ICardPagerAdapter.MAX_ELEVATION_FACTOR - SCALE_VALUE_DEFAULT) * (SCALE_VALUE_DEFAULT - realOffset)));
        }

        final CardView nextCard = mAdapter.getCardViewAt(nextPosition);

        if (nextCard != null) {
            if (mScalingEnabled) {
                nextCard.setScaleX((float) (SCALE_VALUE_DEFAULT + SCALE_VALUE_INCREASED_MINUS_DEFAULT * (realOffset)));
                nextCard.setScaleY((float) (SCALE_VALUE_DEFAULT + SCALE_VALUE_INCREASED_MINUS_DEFAULT * (realOffset)));
            }
            nextCard.setCardElevation((baseElevation + baseElevation * (ICardPagerAdapter.MAX_ELEVATION_FACTOR - SCALE_VALUE_DEFAULT) * (realOffset)));
        }

        mLastOffset = pPositionOffset;
    }

    @Override
    public void onPageSelected(final int pPosition) {

    }

    @Override
    public void onPageScrollStateChanged(final int pState) {

    }
}