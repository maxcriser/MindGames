package com.example.mvmax.mindgames.gamecard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;
import com.example.mvmax.mindgames.games.IBaseGame;

import java.util.List;

public class GameCardInfoPagerAdapter extends FragmentStatePagerAdapter {

    private final int mTabsCount;
    private final List<GameCardTabModel> mTabs;

    GameCardInfoPagerAdapter(final FragmentManager fm, final IBaseGame pGameCardModel) {
        super(fm);
        mTabs = pGameCardModel.getTabs();
        mTabsCount = mTabs.size();
    }

    @Override
    public Fragment getItem(final int pPosition) {
        return mTabs.get(pPosition).getFragment();
    }

    @Override
    public int getCount() {
        return mTabsCount;
    }
}
