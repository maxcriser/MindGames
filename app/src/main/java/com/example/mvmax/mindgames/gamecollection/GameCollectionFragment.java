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
import com.example.mvmax.mindgames.base.BaseFragment;
import com.example.mvmax.mindgames.gamecollection.adapter.GameCollectionPagerAdapter;
import com.example.mvmax.mindgames.gamecollection.listener.IGameCollectionListener;
import com.example.mvmax.mindgames.games.guessthestory.GuessTheStoryGame;
import com.example.mvmax.mindgames.clicklistener.OpenDrawerClickListener;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.gamecollection.transformer.ShadowTransformer;

public class GameCollectionFragment extends BaseFragment {

    private IGameCollectionListener mGameCollectionListener;
    private GameCollectionPagerAdapter mCardAdapter;

    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            mGameCollectionListener.onPageSelected(mCardAdapter.getData().get(position).getPoster());
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

        final ViewPager viewPager = view.findViewById(R.id.view_pager);

        mCardAdapter = new GameCollectionPagerAdapter(getContext());
        mCardAdapter.addCardItem(new GuessTheStoryGame());
        mCardAdapter.addCardItem(new GuessTheStoryGame());
        mCardAdapter.addCardItem(new GuessTheStoryGame());

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
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collection, container, false);
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