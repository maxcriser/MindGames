package com.example.mvmax.mindgames.adapter;

import android.support.v7.widget.CardView;

import com.example.mvmax.mindgames.ContextHolder;
import com.example.mvmax.mindgames.R;

public interface ICardPagerAdapter {

    int MAX_ELEVATION_FACTOR = ContextHolder.get().getResources().getInteger(R.integer.MAX_ELEVATION_FACTOR_VIEW_PAGER);

    int getCount();

    float getBaseElevation();

    CardView getCardViewAt(int pPosition);
}
