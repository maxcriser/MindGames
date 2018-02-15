package com.example.mvmax.mindgames.games;

import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardTabModel;

import java.io.Serializable;
import java.util.List;

public interface IBaseGame extends Serializable {

    Class getActivityClass();

    String getID();

    String getName();

    String getDescription();

    int getPoster();

    boolean isAvailable();

    List<RuleModel> getRules();

    List<ExampleMessageModel> getExample();

    List<GameCardTabModel> getTabs();

}