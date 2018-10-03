package com.example.mvmax.mindgames.gamecard;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.clicklistener.OnBackClickListener;
import com.example.mvmax.mindgames.executable.GameByIdExecutable;
import com.example.mvmax.mindgames.flex.ObservableScrollViewCallbacks;
import com.example.mvmax.mindgames.flex.ScrollState;
import com.example.mvmax.mindgames.flex.ScrollUtils;
import com.example.mvmax.mindgames.flex.Scrollable;
import com.example.mvmax.mindgames.flex.SlidingTabLayout;
import com.example.mvmax.mindgames.flex.TouchInterceptionFrameLayout;
import com.example.mvmax.mindgames.flex.ViewHelper;
import com.example.mvmax.mindgames.games.IBaseGame;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.util.UiUtil;

public class GameCardActivity extends BaseActivity implements ObservableScrollViewCallbacks {

    private static final int INVALID_POINTER = -1;
    public static final String EXTRA_GAME_ID = "extra_game_id";

    private AppCompatImageView mPoster;
    private TextView mName;
    private TextView mDescription;
    private AppCompatButton mPlayButton;
    private IBaseGame mGameCardModel;
    private View mOverlayView;
    private TouchInterceptionFrameLayout mInterceptionLayout;
    private ViewPager mPager;
    private GameCardInfoPagerAdapter mPagerAdapter;
    private VelocityTracker mVelocityTracker;
    private OverScroller mScroller;
    private float mBaseTranslationY;
    private int mMaximumVelocity;
    private int mActivePointerId = INVALID_POINTER;
    private int mSlop;
    private int mFlexibleSpaceHeight;
    private int mTabHeight;
    private boolean mScrolled;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_card);

        final String id = getIntent().getStringExtra(EXTRA_GAME_ID);

        mGameCardModel = new GameByIdExecutable(id).execute();

        init();
        bindHeader();

        ViewCompat.setElevation(findViewById(R.id.header), getResources().getDimension(R.dimen.toolbar_elevation));

        initSlidingTabs();
        initViewConfiguration();
    }

    private void bindHeader() {
        loadGameCardPoster(mGameCardModel.getPosterUrl());

        mPlayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                openGameActivity(GameCardActivity.this, mGameCardModel.getActivityClass());
            }
        });

        UiUtil.setTextOrHide(mName, mGameCardModel.getName());
        UiUtil.setTextOrHide(mDescription, mGameCardModel.getDescription());
    }

    private void init() {
        final Toolbar toolbar = findToolbarView();

        if (toolbar != null) {
            toolbar.getbackIconView().setOnClickListener(new OnBackClickListener(this));
        }

        mPoster = findViewById(R.id.game_fragment_poster);
        mName = findViewById(R.id.game_fragment_header_name);
        mDescription = findViewById(R.id.game_fragment_header_description);
        mPlayButton = findViewById(R.id.game_fragment_play_button);
        mPager = findViewById(R.id.pager);
        mOverlayView = findViewById(R.id.guess_the_story_overlay);
        mFlexibleSpaceHeight = getResources().getDimensionPixelSize(R.dimen.game_fragment_top_layout_height_with_tab_layout);
        mTabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);

        mPagerAdapter = new GameCardInfoPagerAdapter(getSupportFragmentManager(), mGameCardModel);
        mPager.setAdapter(mPagerAdapter);

        findViewById(R.id.pager_wrapper).setPadding(0, mFlexibleSpaceHeight, 0, 0);
        setTitle(null);

        loadGameCardHeader(mGameCardModel.getPosterUrl());
    }

    private void initSlidingTabs() {
        final SlidingTabLayout slidingTabLayout = findViewById(R.id.fragment_game_tab_layout);

        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.tab_indicator_color));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mPager);

        ((ViewGroup.MarginLayoutParams) slidingTabLayout.getLayoutParams()).topMargin = mFlexibleSpaceHeight - mTabHeight;
    }

    private void initViewConfiguration() {
        final ViewConfiguration vc = ViewConfiguration.get(this);

        mSlop = vc.getScaledTouchSlop();
        mMaximumVelocity = vc.getScaledMaximumFlingVelocity();
        mInterceptionLayout = findViewById(R.id.container);
        mInterceptionLayout.setScrollInterceptionListener(mInterceptionListener);
        mScroller = new OverScroller(getApplicationContext());

        ScrollUtils.addOnGlobalLayoutListener(mInterceptionLayout, new Runnable() {

            @Override
            public void run() {
                final FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mInterceptionLayout.getLayoutParams();

                lp.height = getScreenHeight() + mFlexibleSpaceHeight;
                mInterceptionLayout.requestLayout();

                updateFlexibleSpace();
            }
        });
    }

    @Override
    public void onScrollChanged(final int scrollY, final boolean firstScroll, final boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(final ScrollState scrollState) {
    }

    private final TouchInterceptionFrameLayout.TouchInterceptionListener mInterceptionListener = new TouchInterceptionFrameLayout.TouchInterceptionListener() {

        @Override
        public boolean shouldInterceptTouchEvent(final MotionEvent ev, final boolean moving, final float diffX, final float diffY) {
            if (!mScrolled && mSlop < Math.abs(diffX) && Math.abs(diffY) < Math.abs(diffX)) {
                return false;
            }

            final Scrollable scrollable = getCurrentScrollable();

            if (scrollable == null) {
                mScrolled = false;

                return false;
            }

            final int flexibleSpace = mFlexibleSpaceHeight - mTabHeight;
            final int translationY = (int) ViewHelper.getTranslationY(mInterceptionLayout);
            final boolean scrollingUp = diffY > 0;
            final boolean scrollingDown = diffY < 0;

            if (scrollingUp) {
                if (translationY < 0) {
                    mScrolled = true;

                    return true;
                }
            } else if (scrollingDown) {
                if (-flexibleSpace < translationY) {
                    mScrolled = true;

                    return true;
                }
            }

            mScrolled = false;

            return false;
        }

        @Override
        public void onDownMotionEvent(final MotionEvent ev) {
            mActivePointerId = ev.getPointerId(0);
            mScroller.forceFinished(true);

            if (mVelocityTracker == null) {
                mVelocityTracker = VelocityTracker.obtain();
            } else {
                mVelocityTracker.clear();
            }

            mBaseTranslationY = ViewHelper.getTranslationY(mInterceptionLayout);
            mVelocityTracker.addMovement(ev);
        }

        @Override
        public void onMoveMotionEvent(final MotionEvent ev, final float diffX, final float diffY) {
            final int flexibleSpace = mFlexibleSpaceHeight - mTabHeight;
            final float translationY = ScrollUtils.getFloat(ViewHelper.getTranslationY(mInterceptionLayout) + diffY, -flexibleSpace, 0);
            final MotionEvent e = MotionEvent.obtainNoHistory(ev);

            e.offsetLocation(0, translationY - mBaseTranslationY);
            mVelocityTracker.addMovement(e);
            updateFlexibleSpace(translationY);
        }

        @Override
        public void onUpOrCancelMotionEvent(final MotionEvent ev) {
            mScrolled = false;
            mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);

            final int velocityY = (int) mVelocityTracker.getYVelocity(mActivePointerId);

            mActivePointerId = INVALID_POINTER;
            mScroller.forceFinished(true);

            final int baseTranslationY = (int) ViewHelper.getTranslationY(mInterceptionLayout);
            final int minY = -(mFlexibleSpaceHeight - mTabHeight);
            final int maxY = 0;

            mScroller.fling(0, baseTranslationY, 0, velocityY, 0, 0, minY, maxY);

            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    updateLayout();
                }
            });
        }
    };

    private void updateLayout() {
        boolean needsUpdate = false;
        float translationY = 0;

        if (mScroller.computeScrollOffset()) {
            translationY = mScroller.getCurrY();

            final int flexibleSpace = mFlexibleSpaceHeight - mTabHeight;

            if (-flexibleSpace <= translationY && translationY <= 0) {
                needsUpdate = true;
            } else if (translationY < -flexibleSpace) {
                translationY = -flexibleSpace;
                needsUpdate = true;
            } else if (translationY > 0) {
                translationY = 0;
                needsUpdate = true;
            }
        }

        if (needsUpdate) {
            updateFlexibleSpace(translationY);

            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    updateLayout();
                }
            });
        }
    }

    private Scrollable getCurrentScrollable() {
        final Fragment fragment = getCurrentFragment();

        if (fragment == null) {
            return null;
        }

        final View view = fragment.getView();

        if (view == null) {
            return null;
        }

        return (Scrollable) view.findViewById(R.id.scroll);
    }

    private void updateFlexibleSpace() {
        updateFlexibleSpace(ViewHelper.getTranslationY(mInterceptionLayout));
    }

    private void updateFlexibleSpace(final float translationY) {
        ViewHelper.setTranslationY(mInterceptionLayout, translationY);
        final int minOverlayTransitionY = getActionBarSize() - mOverlayView.getHeight();
        ViewHelper.setTranslationY(mPoster, ScrollUtils.getFloat(-translationY / 2, minOverlayTransitionY, 0));

        final float flexibleRange = mFlexibleSpaceHeight - getActionBarSize();
        final float alpha = ScrollUtils.getFloat(-translationY / flexibleRange, 0, 1);
        ViewHelper.setAlpha(mOverlayView, alpha);
    }

    private Fragment getCurrentFragment() {
        return mPagerAdapter.getItemAt(mPager.getCurrentItem());
    }
}
