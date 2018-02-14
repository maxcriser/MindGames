package com.example.mvmax.mindgames.gamecollection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.fragment.BaseFragment;
import com.example.mvmax.mindgames.clicklistener.OpenDrawerClickListener;
import com.example.mvmax.mindgames.gamecollection.adapter.GameCollectionPagerAdapter;
import com.example.mvmax.mindgames.gamecollection.listener.IGameCollectionListener;
import com.example.mvmax.mindgames.gamecollection.transformer.ShadowTransformer;
import com.example.mvmax.mindgames.games.IBaseGame;
import com.example.mvmax.mindgames.games.guessthestory.GuessTheStoryGame;
import com.example.mvmax.mindgames.toolbar.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class GameCollectionFragment extends BaseFragment {

    private IGameCollectionListener mGameCollectionListener;
    private GameCollectionPagerAdapter mCardAdapter;

    public static final  List<IBaseGame> mGames = new ArrayList<IBaseGame>() {{
        add(new GuessTheStoryGame());
        add(new GuessTheStoryGame());
        add(new GuessTheStoryGame());
    }};

    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            mGameCollectionListener.onPageSelected(mCardAdapter.getGames().get(position).getPoster());
        }
    };

    public static Fragment newInstance(@NonNull final IGameCollectionListener pGameCollectionListener) {
        final GameCollectionFragment gameCollectionFragment = new GameCollectionFragment();

        gameCollectionFragment.mGameCollectionListener = pGameCollectionListener;

        return gameCollectionFragment;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarPadding();
        initViews();
        initPager();
    }

    private void initPager() {
        final View view = getView();

        if (view == null) {
            return;
        }

        mCardAdapter = new GameCollectionPagerAdapter(getContext(), mGames);

        final ViewPager viewPager = view.findViewById(R.id.view_pager);
        final ShadowTransformer cardShadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);

        cardShadowTransformer.enableScaling();

        viewPager.setAdapter(mCardAdapter);
        viewPager.setPageTransformer(false, cardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);

        mOnPageChangeListener.onPageSelected(0);
    }

    private void initViews() {
        final View view = getView();

        if (view == null) {
            return;
        }

        mGameCollectionListener.onPageSelected(R.drawable.template_blurred_background);

        final Toolbar toolbar = view.findViewById(R.id.toolbar_view);
        toolbar.getMenuIconView().setOnClickListener(new OpenDrawerClickListener(getContext()));
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        return pInflater.inflate(R.layout.fragment_collection, pContainer, false);
    }

    @Override
    public void onPause() {
        mGameCollectionListener.onPause();

        super.onPause();
    }

    @Override
    public void onResume() {
        mGameCollectionListener.onResume();

        super.onResume();
    }
}