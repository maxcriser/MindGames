package com.example.mvmax.mindgames.model;

import java.io.Serializable;

public class GameCardModel implements Serializable {

    private final int mPoster;
    private final String mName;
    private final String mDescription;

    public GameCardModel(final int pPoster, final String pName, final String pDescription) {
        mPoster = pPoster;
        mName = pName;
        mDescription = pDescription;
    }

    public int getPoster() {
        return mPoster;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }
}