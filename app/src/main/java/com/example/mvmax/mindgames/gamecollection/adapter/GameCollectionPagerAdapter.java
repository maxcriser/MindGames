package com.example.mvmax.mindgames.gamecollection.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.base.BaseActivity;
import com.example.mvmax.mindgames.games.BaseGame;

import java.util.ArrayList;
import java.util.List;

public class GameCollectionPagerAdapter extends PagerAdapter implements IGameCollectionPagerAdapter {

    private final List<CardView> mViews;
    private final List<BaseGame> mData;
    private float mBaseElevation;
    private final Context mContext;

    public GameCollectionPagerAdapter(final Context pContext) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        mContext = pContext;
    }

    public List<BaseGame> getData() {
        return mData;
    }

    public void addCardItem(final BaseGame pItem) {
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
    public boolean isViewFromObject(@NonNull final View pView, @NonNull final Object pObject) {
        return pView == pObject;
    }

    @Override
    public Object instantiateItem(@NonNull final ViewGroup pContainer, final int pPosition) {
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
    public void destroyItem(@NonNull final ViewGroup pContainer, final int pPosition, @NonNull final Object pObject) {
        pContainer.removeView((View) pObject);
        mViews.set(pPosition, null);
    }

    private void bind(final BaseGame pItem, final View pView) {
        final AppCompatImageView poster = pView.findViewById(R.id.card_poster);
        poster.setImageResource(pItem.getPoster());
        poster.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                ((BaseActivity) mContext).showGameFragment(pItem);
            }
        });
    }
}