package com.example.mvmax.mindgames.gamecard.model;

import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.model.IModel;

import java.io.Serializable;
import java.util.List;

public class GameCardModel implements Serializable {

    private String id;
    private String name;
    private String description;

    private boolean isAvailable;
    private boolean isPaid;

    private List<RuleModel> rules;
    private List<ExampleMessageModel> example;

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean isPaid() {
        return isPaid;
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