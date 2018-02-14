package com.example.mvmax.mindgames.gamecard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.clicklistener.OnBackClickListener;
import com.example.mvmax.mindgames.executable.GameByIdExecutable;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;
import com.example.mvmax.mindgames.games.IBaseGame;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.util.UiUtil;

import java.util.List;

@SuppressLint("Registered")
public class GameCardActivity extends BaseActivity {

    public static final String EXTRA_GAME_ID = "extra_game_id";

    private AppCompatImageView mPoster;
    private TextView mName;
    private TextView mDescription;
    private AppCompatButton mPlayButton;
    private IBaseGame mGameCardModel;

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_game);

        final String id = getIntent().getStringExtra(EXTRA_GAME_ID);

        mGameCardModel = new GameByIdExecutable(id).execute();

        setStatusBarPadding();
        initViews();
        bindHeader();
        bindViewPager();
    }

    private void bindViewPager() {
        final TabLayout tabLayout = findViewById(R.id.fragment_game_tab_layout);
        final List<GameCardTabModel> tabs = mGameCardModel.getTabs();

        for (int i = 0; i < tabs.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabs.get(i).getTitle()));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.game_info_view_pager);

        final GameCardInfoPagerAdapter adapter = new GameCardInfoPagerAdapter(getSupportFragmentManager(), mGameCardModel);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(final TabLayout.Tab tab) {

            }
        });
    }

    private void bindHeader() {
        final int poster = mGameCardModel.getPoster();

        mPoster.setImageResource(poster);
        mPlayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                openGameActivity(GameCardActivity.this, mGameCardModel.getActivityClass());
            }
        });

        UiUtil.setTextOrHide(mName, mGameCardModel.getName());
        UiUtil.setTextOrHide(mDescription, mGameCardModel.getDescription());
    }

    private void initViews() {
        final Toolbar toolbar = findViewById(R.id.toolbar_view);
        toolbar.getbackIconView().setOnClickListener(new OnBackClickListener(this));

        mPoster = findViewById(R.id.game_fragment_poster);
        mName = findViewById(R.id.game_fragment_header_name);
        mDescription = findViewById(R.id.game_fragment_header_description);
        mPlayButton = findViewById(R.id.game_fragment_play_button);
    }
}