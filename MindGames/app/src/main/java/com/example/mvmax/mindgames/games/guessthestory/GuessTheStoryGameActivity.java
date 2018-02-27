package com.example.mvmax.mindgames.games.guessthestory;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.clicklistener.OnBackClickListener;
import com.example.mvmax.mindgames.flex.ObservableRecyclerView;
import com.example.mvmax.mindgames.flex.ObservableScrollViewCallbacks;
import com.example.mvmax.mindgames.flex.ScrollState;
import com.example.mvmax.mindgames.flex.ScrollUtils;
import com.example.mvmax.mindgames.flex.ViewHelper;
import com.example.mvmax.mindgames.games.guessthestory.adapter.GuessTheStoryAdapter;
import com.example.mvmax.mindgames.games.guessthestory.executable.GuessTheStoryGameExecutable;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameModel;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.squareup.picasso.Picasso;

public class GuessTheStoryGameActivity extends BaseActivity implements ObservableScrollViewCallbacks {

    private static final float MAX_TEXT_SCALE_DELTA = 0.3f;

    private ImageView mImageView;
    private View mOverlayView;
    private View mRecyclerViewBackground;
    private TextView mTitleView;
    private int mActionBarSize;
    private int mFlexibleSpaceImageHeight;
    private View mToolbarSecondBackgroundView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_story_game);

        final GuessTheStoryGameModel gameModel = new GuessTheStoryGameExecutable().execute();

        final Toolbar toolbar = findToolbarView();

        if (toolbar != null) {
            toolbar.setAllCaps(true);
            toolbar.setTitle(gameModel.getTitle());
            toolbar.addStatusBarHeight(getStatusBarHeight());
            toolbar.getbackIconView().setOnClickListener(new OnBackClickListener(this));
            mToolbarSecondBackgroundView = toolbar.getSecondBackgroundView();
        }

        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.guess_the_story_activity_header_height);
        mActionBarSize = getActionBarSize();

        final ObservableRecyclerView recyclerView = findViewById(R.id.guess_the_story_recycler);
        recyclerView.setScrollViewCallbacks(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        final View headerView = LayoutInflater.from(this).inflate(R.layout.recycler_header, null);
        headerView.post(new Runnable() {

            @Override
            public void run() {
                headerView.getLayoutParams().height = mFlexibleSpaceImageHeight;
            }
        });

        recyclerView.setAdapter(new GuessTheStoryAdapter(gameModel.getList(), headerView));

        mImageView = findViewById(R.id.guess_the_story_image);
        mOverlayView = findViewById(R.id.guess_the_story_overlay);

        Picasso.with(this).load(gameModel.getPosterUrl()).into(mImageView);

        mTitleView = findViewById(R.id.title);
        mTitleView.setText(getTitle());
        setTitle(null);

        mRecyclerViewBackground = findViewById(R.id.guess_the_story_list_background);

        final float scale = 1 + MAX_TEXT_SCALE_DELTA;

        mRecyclerViewBackground.post(new Runnable() {

            @Override
            public void run() {
                ViewHelper.setTranslationY(mRecyclerViewBackground, mFlexibleSpaceImageHeight);
            }
        });

        ViewHelper.setTranslationY(mOverlayView, mFlexibleSpaceImageHeight);

        mTitleView.post(new Runnable() {

            @Override
            public void run() {
                ViewHelper.setTranslationY(mTitleView, (int) (mFlexibleSpaceImageHeight - mTitleView.getHeight() * scale));
                ViewHelper.setPivotX(mTitleView, 0);
                ViewHelper.setPivotY(mTitleView, 0);
                ViewHelper.setScaleX(mTitleView, scale);
                ViewHelper.setScaleY(mTitleView, scale);
            }
        });
    }

    @Override
    public void onScrollChanged(final int scrollY, final boolean firstScroll, final boolean dragging) {
        final float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        final int minOverlayTransitionY = mActionBarSize - mOverlayView.getHeight();

        ViewHelper.setTranslationY(mOverlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(mImageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

        // Translate list background
        ViewHelper.setTranslationY(mRecyclerViewBackground, Math.max(0, -scrollY + mFlexibleSpaceImageHeight));

        // Change alpha of overlay
        final float alphaValue = ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1);
        ViewHelper.setAlpha(mOverlayView, alphaValue);
        ViewHelper.setAlpha(mToolbarSecondBackgroundView, alphaValue);

        // Scale title text
        final float scale = 1 + ScrollUtils.getFloat((flexibleRange - scrollY) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
        setPivotXToTitle();
        ViewHelper.setPivotY(mTitleView, 0);
        ViewHelper.setScaleX(mTitleView, scale);
        ViewHelper.setScaleY(mTitleView, scale);

        // Translate title text
        final int maxTitleTranslationY = (int) (mFlexibleSpaceImageHeight - mTitleView.getHeight() * scale);
        final int titleTranslationY = maxTitleTranslationY - scrollY;
        ViewHelper.setTranslationY(mTitleView, titleTranslationY);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(final ScrollState scrollState) {
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setPivotXToTitle() {
        final Configuration config = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            ViewHelper.setPivotX(mTitleView, findViewById(android.R.id.content).getWidth());
        } else {
            ViewHelper.setPivotX(mTitleView, 0);
        }
    }
}
