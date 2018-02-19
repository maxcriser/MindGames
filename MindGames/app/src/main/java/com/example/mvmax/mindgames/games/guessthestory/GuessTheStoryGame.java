package com.example.mvmax.mindgames.games.guessthestory;

import android.content.Context;

import com.example.mvmax.mindgames.ContextHolder;
import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.constants.Constant;
import com.example.mvmax.mindgames.executable.GameInfoByIdExecutable;
import com.example.mvmax.mindgames.gamecard.info.GameCardExampleFragment;
import com.example.mvmax.mindgames.gamecard.info.GameCardRulesFragment;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;
import com.example.mvmax.mindgames.games.IBaseGame;

import java.util.ArrayList;
import java.util.List;

public class GuessTheStoryGame implements IBaseGame {

    private final GameCardModel mGameCardModel;

    public GuessTheStoryGame() {
        mGameCardModel = new GameInfoByIdExecutable(getID()).execute();
    }

    @Override
    public String getID() {
        return Constant.GameID.GUESS_THE_STORY;
    }

    @Override
    public int getPoster() {
        return R.drawable.template_poster;
    }

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

        if (!rules.isEmpty()) {
            tabs.add(new GameCardTabModel(context.getString(R.string.game_card_rules), GameCardRulesFragment.newInstance(getRules())));
        }

        if (!example.isEmpty()) {
            tabs.add(new GameCardTabModel(context.getString(R.string.game_card_example), GameCardExampleFragment.newInstance(getExample())));
        }

        return tabs;
    }

    @Override
    public Class getActivityClass() {
        return GuessTheStoryGameActivity.class;
    }

    @Override
    public boolean isAvailable() {
        return mGameCardModel.isAvailable();
    }
}