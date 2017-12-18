package com.example.mvmax.mindgames.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.model.CardModel;

import java.util.ArrayList;
import java.util.List;

public class ICardPagerPagerAdapter extends PagerAdapter implements ICardPagerAdapter {

    private final List<CardView> mViews;
    private final List<CardModel> mData;
    private float mBaseElevation;

    public ICardPagerPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(final CardModel pItem) {
        mViews.add(null);
        mData.add(pItem);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(final int pPosition) {
        return mViews.get(pPosition);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(final View pView, final Object pObject) {
        return pView == pObject;
    }

    @Override
    public Object instantiateItem(final ViewGroup pContainer, final int pPosition) {
        final View view = LayoutInflater.from(pContainer.getContext()).inflate(R.layout.adapter_card, pContainer, false);
        pContainer.addView(view);
        bind(mData.get(pPosition), view);

        final CardView cardView = view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(pPosition, cardView);

        return view;
    }

    @Override
    public void destroyItem(final ViewGroup pContainer, final int pPosition, final Object pObject) {
        pContainer.removeView((View) pObject);
        mViews.set(pPosition, null);
    }

    private void bind(final CardModel pItem, final View pView) {
        final AppCompatImageView poster = pView.findViewById(R.id.card_poster);
        poster.setImageDrawable(pItem.getPoster());
    }
}