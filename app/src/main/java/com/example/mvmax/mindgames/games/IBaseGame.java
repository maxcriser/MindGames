package com.example.mvmax.mindgames.games;

import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;

import java.util.List;

public interface IBaseGame {

    String getId();

    int getPoster();

    List<RuleModel> getRules();

    List<ExampleMessageModel> getExample();

    String getName();

    String getDescription();

    boolean isAvailable();

    List<GameCardTabModel> getTabList();

    Class getActivityClass();

}