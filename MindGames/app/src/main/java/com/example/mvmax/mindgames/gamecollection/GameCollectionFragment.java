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
import com.example.mvmax.mindgames.clicklistener.OpenDrawerClickListener;
import com.example.mvmax.mindgames.fragment.BaseFragment;
import com.example.mvmax.mindgames.gamecollection.adapter.GameCollectionPagerAdapter;
import com.example.mvmax.mindgames.gamecollection.transformer.ShadowTransformer;
import com.example.mvmax.mindgames.toolbar.Toolbar;

public class GameCollectionFragment extends BaseFragment {

    private GameCollectionPagerAdapter mCardAdapter;

    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(final int pPosition) {
            setBackgroundDrawable(mCardAdapter.getGames().get(pPosition).getPoster());
        }
    };

    public static Fragment newInstance() {
        return new GameCollectionFragment();
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

        mCardAdapter = new GameCollectionPagerAdapter(getContext(), GameCollection.GAMES);

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

        setBackgroundDrawable(R.drawable.template_blurred_background);

        final Toolbar toolbar = view.findViewById(R.id.toolbar_view);
        toolbar.getMenuIconView().setOnClickListener(new OpenDrawerClickListener(getContext()));
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        return pInflater.inflate(R.layout.fragment_collection, pContainer, false);
    }

    @Override
    public void onPause() {
        disableDrawer();

        super.onPause();
    }

    @Override
    public void onResume() {
        enableDrawer();

        super.onResume();
    }
}