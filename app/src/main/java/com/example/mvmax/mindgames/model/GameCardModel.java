package com.example.mvmax.mindgames.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class GameCardModel implements Serializable {

    private final Drawable mPoster;
    private final String mName;
    private final String mDescription;

    public GameCardModel(final Drawable pPoster, final String pName, final String pDescription) {
        mPoster = pPoster;
        mName = pName;
        mDescription = pDescription;
    }

    public Drawable getPoster() {
        return mPoster;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }
}