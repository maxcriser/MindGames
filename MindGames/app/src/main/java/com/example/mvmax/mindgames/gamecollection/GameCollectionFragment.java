package com.example.mvmax.mindgames.gamecollection;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.clicklistener.BuyPremiumClickListener;
import com.example.mvmax.mindgames.clicklistener.OpenDrawerClickListener;
import com.example.mvmax.mindgames.config.AppConfig;
import com.example.mvmax.mindgames.fragment.BaseFragment;
import com.example.mvmax.mindgames.gamecollection.adapter.GameCollectionPagerAdapter;
import com.example.mvmax.mindgames.gamecollection.transformer.ShadowTransformer;
import com.example.mvmax.mindgames.games.IBaseGame;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.util.AppUtils;

public class GameCollectionFragment extends BaseFragment {

    private TextView mGameTitle;
    private TextView mGameDescription;
    private View mGamePlayButton;
    private View mGameBuyButton;
    private View mGameOpenButton;
    private GameCollectionPagerAdapter mCardAdapter;

    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(final int pPosition) {
            final IBaseGame baseGame = mCardAdapter.getGames().get(pPosition);

            mGameTitle.setText(baseGame.getName());

            final View.OnClickListener openClickListener = new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    ((BaseActivity) v.getContext()).showGameActivity(baseGame.getID());

                }
            };

            if (!AppUtils.isContentAvailable(baseGame.isPaid())) {
                mGameDescription.setText(R.string.available_for_premium);
                mGamePlayButton.setVisibility(View.GONE);
                mGameBuyButton.setVisibility(View.VISIBLE);
                mGameOpenButton.setVisibility(View.VISIBLE);
                mGameOpenButton.setOnClickListener(openClickListener);
            } else {
                mGameDescription.setText(AppUtils.isBoughtContent(baseGame.isPaid()) ? R.string.available_for_you_now : R.string.available_for_free_play);
                mGamePlayButton.setOnClickListener(openClickListener);
                mGamePlayButton.setVisibility(View.VISIBLE);
                mGameBuyButton.setVisibility(View.GONE);
                mGameOpenButton.setVisibility(View.GONE);
            }

            setBackgroundUrl(baseGame.getPosterIntDrawable());
        }
    };

    private void openGame(final Context pContext, final IBaseGame pIBaseGame) {
        ((BaseActivity) pContext).showGameActivity(pIBaseGame.getID());
    }

    public static Fragment newInstance() {
        return new GameCollectionFragment();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
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

    private void initViews(final View pView) {
        mGameTitle = pView.findViewById(R.id.game_title);
        mGameDescription = pView.findViewById(R.id.game_description);
        mGamePlayButton = pView.findViewById(R.id.game_play_button);
        mGameBuyButton = pView.findViewById(R.id.game_buy_button);
        mGameOpenButton = pView.findViewById(R.id.game_open_button);
        mGameBuyButton.setOnClickListener(new BuyPremiumClickListener());

        setBackgroundDrawable(R.drawable.template_blurred_background);

        final Toolbar toolbar = findToolbarView();

        if (toolbar != null) {
            toolbar.getMenuIconView().setOnClickListener(new OpenDrawerClickListener(getContext()));
        }
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