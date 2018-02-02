package com.example.mvmax.mindgames.test;

import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;

import java.util.List;

public class GameCard {

    private String id;
    private String name;
    private String description;

    private boolean isAvailable;

    private List<RuleModel> rules;
    private List<ExampleMessageModel> example;

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<RuleModel> getRules() {
        return rules;
    }

    public List<ExampleMessageModel> getExample() {
        return example;
    }
}
