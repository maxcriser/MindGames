package com.example.mvmax.mindgames.gamecard.model;

import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.games.IBaseGame;

import java.io.Serializable;
import java.util.List;

public class GameCardModel implements Serializable {

    private final IBaseGame mIBaseGame;

    public GameCardModel(final IBaseGame pIBaseGame) {
        mIBaseGame = pIBaseGame;
    }

    public Class getActivityClass() {
        return mIBaseGame.getActivityClass();
    }

    public List<GameCardTabModel> getTabList() {
        return mIBaseGame.getTabList();
    }

    public int getPoster() {
        return mIBaseGame.getPoster();
    }

    public String getName() {
        return mIBaseGame.getName();
    }

    public List<RuleModel> getRuleList() {
        return mIBaseGame.getRules();
    }

    public List<ExampleMessageModel> getDialogExampleModelList() {
        return mIBaseGame.getExample();
    }

    public String getDescription() {
        return mIBaseGame.getDescription();
    }
}