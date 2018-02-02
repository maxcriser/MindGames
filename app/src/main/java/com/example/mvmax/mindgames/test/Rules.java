package com.example.mvmax.mindgames.test;

import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    private final List<RuleModel> rules;

    public Rules() {
        rules = new ArrayList<>();
    }

    public List<RuleModel> getList() {
        return rules;
    }
}
