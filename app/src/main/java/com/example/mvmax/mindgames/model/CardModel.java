package com.example.mvmax.mindgames.model;

import android.graphics.drawable.Drawable;

public class CardModel {

    private final Drawable mPoster;

    public CardModel(final Drawable pPoster) {
        mPoster = pPoster;
    }

    public Drawable getPoster() {
        return mPoster;
    }
}