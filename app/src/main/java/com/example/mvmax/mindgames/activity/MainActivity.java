package com.example.mvmax.mindgames.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.mvmax.mindgames.model.CardModel;
import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.transformer.ShadowTransformer;
import com.example.mvmax.mindgames.adapter.ICardPagerPagerAdapter;
import com.example.mvmax.mindgames.util.UiUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);
        setBackground(UiUtils.getDrawable(this, R.drawable.template_blurred_background));
        setStatusBarPadding();

        final ViewPager viewPager = findViewById(R.id.viewPager);

        final ICardPagerPagerAdapter cardAdapter = new ICardPagerPagerAdapter();
        cardAdapter.addCardItem(new CardModel(UiUtils.getDrawable(this, R.drawable.template_card_poster)));
        cardAdapter.addCardItem(new CardModel(UiUtils.getDrawable(this, R.drawable.template_card_poster)));
        cardAdapter.addCardItem(new CardModel(UiUtils.getDrawable(this, R.drawable.template_card_poster)));
        cardAdapter.addCardItem(new CardModel(UiUtils.getDrawable(this, R.drawable.template_card_poster)));
        cardAdapter.addCardItem(new CardModel(UiUtils.getDrawable(this, R.drawable.template_card_poster)));
        cardAdapter.addCardItem(new CardModel(UiUtils.getDrawable(this, R.drawable.template_card_poster)));
        cardAdapter.addCardItem(new CardModel(UiUtils.getDrawable(this, R.drawable.template_card_poster)));

        final ShadowTransformer cardShadowTransformer = new ShadowTransformer(viewPager, cardAdapter);
        cardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(cardAdapter);
        viewPager.setPageTransformer(false, cardShadowTransformer);
        viewPager.setOffscreenPageLimit(4);
    }
}