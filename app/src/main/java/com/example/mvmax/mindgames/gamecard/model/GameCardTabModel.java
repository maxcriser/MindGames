package com.example.mvmax.mindgames.gamecard.model;

import android.support.v4.app.Fragment;

public class GameCardTabModel {

    private final String mTitle;
    private final Fragment mFragment;

    public GameCardTabModel(final String pTitle, final Fragment pFragment) {
        mTitle = pTitle;
        mFragment = pFragment;
    }

    public String getTitle() {
        return mTitle;
    }

    public Fragment getFragment() {
        return mFragment;
    }
}
