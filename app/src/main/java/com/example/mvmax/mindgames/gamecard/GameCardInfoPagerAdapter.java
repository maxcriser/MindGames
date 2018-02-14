package com.example.mvmax.mindgames.gamecard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.mvmax.mindgames.flex.CacheFragmentStatePagerAdapter;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;
import com.example.mvmax.mindgames.games.IBaseGame;

import java.util.List;

public class GameCardInfoPagerAdapter extends CacheFragmentStatePagerAdapter {

    private final List<GameCardTabModel> mTabs;

    public GameCardInfoPagerAdapter(final FragmentManager fm, final IBaseGame pGameCardModel) {
        super(fm);
        mTabs = pGameCardModel.getTabs();
    }

    @Override
    protected Fragment createItem(final int pPosition) {
        return mTabs.get(pPosition).getFragment();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public CharSequence getPageTitle(final int pPosition) {
        return mTabs.get(pPosition).getTitle();
    }
}
