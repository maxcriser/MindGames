package com.example.mvmax.mindgames.games.guessthestory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.clicklistener.OnBackClickListener;
import com.example.mvmax.mindgames.flex.ObservableRecyclerView;
import com.example.mvmax.mindgames.flex.ObservableScrollViewCallbacks;
import com.example.mvmax.mindgames.flex.ScrollState;
import com.example.mvmax.mindgames.flex.ScrollUtils;
import com.example.mvmax.mindgames.flex.ViewHelper;
import com.example.mvmax.mindgames.games.IBaseGame;
import com.example.mvmax.mindgames.games.guessthestory.adapter.GuessTheStoryAdapter;
import com.example.mvmax.mindgames.games.guessthestory.executable.GuessTheStoryGameExecutable;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameModel;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.squareup.picasso.Picasso;

public class GuessTheStoryGameActivity extends BaseActivity implements ObservableScrollViewCallbacks {

    private View mTopImageView;
    private View mOverlayView;
    private View mRecyclerViewBackground;
    private int mActionBarSize;
    private int mFlexibleSpaceImageHeight;
    private View mToolbarSecondBackgroundView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_story_game);

        final Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        if (bundle != null) {
            final IBaseGame baseGame = (IBaseGame) bundle.getSerializable(BaseActivity.BASE_GAME_MODEL_TO_NEXT_ACTIVITY);

            if (baseGame != null) {
                mTopImageView = findViewById(R.id.guess_the_story_block);


                Picasso.with(this).load(baseGame.getPosterIntDrawable()).into((ImageView) findViewById(R.id.guess_the_story_image));
            }
        }

        final GuessTheStoryGameModel gameModel = new GuessTheStoryGameExecutable().execute();

        final Toolbar toolbar = findToolbarView();

        if (toolbar != null) {
            toolbar.setAllCaps(true);
            toolbar.setTitle(gameModel.getTitle());
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

        mOverlayView = findViewById(R.id.guess_the_story_overlay);

        mRecyclerViewBackground = findViewById(R.id.guess_the_story_list_background);

        mRecyclerViewBackground.post(new Runnable() {

            @Override
            public void run() {
                ViewHelper.setTranslationY(mRecyclerViewBackground, mFlexibleSpaceImageHeight);
            }
        });

        ViewHelper.setTranslationY(mOverlayView, mFlexibleSpaceImageHeight);
    }

    @Override
    public void onScrollChanged(final int scrollY, final boolean firstScroll, final boolean dragging) {
        final float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        final int minOverlayTransitionY = mActionBarSize - mOverlayView.getHeight();

        ViewHelper.setTranslationY(mOverlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(mTopImageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

        // Translate list background
        ViewHelper.setTranslationY(mRecyclerViewBackground, Math.max(0, -scrollY + mFlexibleSpaceImageHeight));

        // Change alpha of overlay
        final float alphaValue = ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1);
        ViewHelper.setAlpha(mOverlayView, alphaValue);
        ViewHelper.setAlpha(mToolbarSecondBackgroundView, alphaValue);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(final ScrollState scrollState) {
    }
}
