package com.example.mvmax.mindgames.games.guessthestory;

import com.example.mvmax.mindgames.ContextHolder;
import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.gamecard.info.GameCardExampleFragment;
import com.example.mvmax.mindgames.gamecard.info.GameCardRulesFragment;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;
import com.example.mvmax.mindgames.games.BaseGame;

import java.util.ArrayList;
import java.util.List;

public class GuessTheStoryGame extends BaseGame {

    @Override
    public int getPoster() {
        return R.drawable.template_poster;
    }

    @Override
    public String getName() {
        return getString(R.string.guess_the_story_name);
    }

    @Override
    public String getDescription() {
        return getString(R.string.guess_the_story_description);
    }

    @Override
    public List<RuleModel> getRuleList() {
        final List<RuleModel> rulesList = new ArrayList<>();
        rulesList.add(new RuleModel(getString(R.string.guess_the_story_step_1), getString(R.string.guess_the_story_step_1_des)));
        rulesList.add(new RuleModel(getString(R.string.guess_the_story_step_2), getString(R.string.guess_the_story_step_2_des)));
        rulesList.add(new RuleModel(getString(R.string.guess_the_story_step_3), getString(R.string.guess_the_story_step_3_des)));
        rulesList.add(new RuleModel(getString(R.string.guess_the_story_step_4), getString(R.string.guess_the_story_step_4_des)));
        rulesList.add(new RuleModel(getString(R.string.guess_the_story_step_5), getString(R.string.guess_the_story_step_5_des)));

        return rulesList;
    }

    @Override
    public List<ExampleMessageModel> getDialogMessageList() {
        final List<ExampleMessageModel> exampleList = new ArrayList<>();
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_1), ExampleMessageModel.DialogMessageType.INFO));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_2), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_3), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.yes_with_dot), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_4), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.no_with_dot), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_5), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.no_with_dot), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_6), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.yes_with_dot), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_7), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_8), ExampleMessageModel.DialogMessageType.INFO));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_9), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.yes_with_dot), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_10), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.yes_with_dot), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_11), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.yes_with_dot), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_12), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.yes_with_dot), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_13), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_14), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_15), ExampleMessageModel.DialogMessageType.PLAYER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_16), ExampleMessageModel.DialogMessageType.PRESENTER));
        exampleList.add(new ExampleMessageModel(getString(R.string.guess_the_story_ex_17), ExampleMessageModel.DialogMessageType.FINISH));

        return exampleList;
    }

    @Override
    public List<GameCardTabModel> getTabList() {
        final List<GameCardTabModel> tabListTwo = new ArrayList<>();
        tabListTwo.add(new GameCardTabModel(ContextHolder.get().getString(R.string.game_card_rules), GameCardRulesFragment.newInstance(getRuleList())));
        tabListTwo.add(new GameCardTabModel(ContextHolder.get().getString(R.string.game_card_example), GameCardExampleFragment.newInstance(getDialogMessageList())));

        return tabListTwo;
    }

    @Override
    public Class getActivityClass() {
        return GuessTheStoryGameActivity.class;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}