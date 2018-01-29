package com.example.mvmax.mindgames.games;

import com.example.mvmax.mindgames.ContextHolder;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;

import java.util.List;

public abstract class BaseGame {

    protected String getString(final int pStringId) {
        return ContextHolder.get().getString(pStringId);
    }

    public abstract int getPoster();

    public abstract String getName();

    public abstract String getDescription();

    public abstract List<RuleModel> getRuleList();

    public abstract List<ExampleMessageModel> getDialogMessageList();

    public abstract List<GameCardTabModel> getTabList();

    public abstract Class getActivityClass();

    public abstract boolean isAvailable();

}