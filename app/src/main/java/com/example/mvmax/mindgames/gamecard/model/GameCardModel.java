package com.example.mvmax.mindgames.gamecard.model;

import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.games.BaseGame;

import java.io.Serializable;
import java.util.List;

public class GameCardModel implements Serializable {

    private final BaseGame mBaseGame;

    public GameCardModel(final BaseGame pBaseGame) {
        mBaseGame = pBaseGame;
    }

    public Class getActivityClass() {
        return mBaseGame.getActivityClass();
    }

    public List<GameCardTabModel> getTabList() {
        return mBaseGame.getTabList();
    }

    public int getPoster() {
        return mBaseGame.getPoster();
    }

    public String getName() {
        return mBaseGame.getName();
    }

    public List<RuleModel> getRuleList() {
        return mBaseGame.getRuleList();
    }

    public List<ExampleMessageModel> getDialogExampleModelList() {
        return mBaseGame.getDialogMessageList();
    }

    public String getDescription() {
        return mBaseGame.getDescription();
    }
}