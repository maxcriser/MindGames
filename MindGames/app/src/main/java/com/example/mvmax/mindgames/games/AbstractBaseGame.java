package com.example.mvmax.mindgames.games;

import android.content.Context;

import com.example.mvmax.mindgames.ContextHolder;
import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.executable.GameInfoByIdExecutable;
import com.example.mvmax.mindgames.gamecard.info.GameCardExampleFragment;
import com.example.mvmax.mindgames.gamecard.info.GameCardRulesFragment;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;
import com.example.mvmax.mindgames.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBaseGame implements IBaseGame {

    private final GameCardModel mGameCardModel;

    public AbstractBaseGame() {
        mGameCardModel = new GameInfoByIdExecutable(getID()).execute();
    }

    public abstract String getID();

    public abstract String getPosterUrl();

    public abstract Class getActivityClass();

    @Override
    public String getName() {
        return mGameCardModel.getName();
    }

    @Override
    public String getDescription() {
        return mGameCardModel.getDescription();
    }

    @Override
    public List<RuleModel> getRules() {
        return mGameCardModel.getRules();
    }

    @Override
    public List<ExampleMessageModel> getExample() {
        return mGameCardModel.getExample();
    }

    @Override
    public List<GameCardTabModel> getTabs() {
        final Context context = ContextHolder.get();

        final List<RuleModel> rules = getRules();
        final List<ExampleMessageModel> example = getExample();
        final List<GameCardTabModel> tabs = new ArrayList<>();

        if (!ListUtil.isEmpty(rules)) {
            tabs.add(new GameCardTabModel(context.getString(R.string.game_card_rules), GameCardRulesFragment.newInstance(rules)));
        }

        if (!ListUtil.isEmpty(example)) {
            tabs.add(new GameCardTabModel(context.getString(R.string.game_card_example), GameCardExampleFragment.newInstance(example)));
        }

        return tabs;
    }

    @Override
    public boolean isAvailable() {
        return mGameCardModel.isAvailable();
    }
}