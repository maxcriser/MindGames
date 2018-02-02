package com.example.mvmax.mindgames.games;

import com.example.mvmax.mindgames.ContextHolder;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;
import com.example.mvmax.mindgames.test.Examples;
import com.example.mvmax.mindgames.test.Rules;

import java.util.List;

public abstract class IBaseGame {

    protected String getString(final int pStringId) {
        return ContextHolder.get().getString(pStringId);
    }

    public abstract String getId();

    public abstract int getPoster();

    public abstract List<RuleModel> getRules();

    public abstract Examples getExample();

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean isAvailable();

    public abstract List<GameCardTabModel> getTabList();

    public abstract Class getActivityClass();

}