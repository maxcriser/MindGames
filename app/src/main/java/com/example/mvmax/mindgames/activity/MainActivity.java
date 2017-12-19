package com.example.mvmax.mindgames.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.adapter.CardPagerPagerAdapter;
import com.example.mvmax.mindgames.model.GameCardModel;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.transformer.ShadowTransformer;
import com.example.mvmax.mindgames.util.UiUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    @Override
    public void onClick(final View pView) {
        final int id = pView.getId();

        switch (id) {
            case R.id.toolbar_view_menu:
                openDrawer();

                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initPager();
    }

    private void initPager() {
        final ViewPager viewPager = findViewById(R.id.view_pager);

        final CardPagerPagerAdapter cardAdapter = new CardPagerPagerAdapter(this);
        cardAdapter.addCardItem(new GameCardModel(UiUtils.getDrawable(this, R.drawable.template_poster), "Name Of Game", "The best of the best of the best game in the World."));
        cardAdapter.addCardItem(new GameCardModel(UiUtils.getDrawable(this, R.drawable.template_poster_2), "Name Of Game", "The best of the best of the best game in the World."));
        cardAdapter.addCardItem(new GameCardModel(UiUtils.getDrawable(this, R.drawable.template_poster_3), "Name Of Game", "The best of the best of the best game in the World."));
        cardAdapter.addCardItem(new GameCardModel(UiUtils.getDrawable(this, R.drawable.template_poster_4), "Name Of Game", "The best of the best of the best game in the World."));
        cardAdapter.addCardItem(new GameCardModel(UiUtils.getDrawable(this, R.drawable.template_poster_5), "Name Of Game", "The best of the best of the best game in the World."));
        cardAdapter.addCardItem(new GameCardModel(UiUtils.getDrawable(this, R.drawable.template_poster_6), "Name Of Game", "The best of the best of the best game in the World."));

        final ShadowTransformer cardShadowTransformer = new ShadowTransformer(viewPager, cardAdapter);
        cardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(cardAdapter);
        viewPager.setPageTransformer(false, cardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                setBackground(cardAdapter.getData().get(position).getPoster());
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
    }

    private void initViews() {
        // TODO: 19.12.2017 design
        initDrawer();

        setBackground(UiUtils.getDrawable(this, R.drawable.template_blurred_background));
        setStatusBarPadding();

        mToolbar = findViewById(R.id.toolbar_view);
        mToolbar.getMenuIconView().setOnClickListener(this);
    }
}