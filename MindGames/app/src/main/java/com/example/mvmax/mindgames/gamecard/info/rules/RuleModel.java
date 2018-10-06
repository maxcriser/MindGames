package com.example.mvmax.mindgames.gamecard.info.rules;

import java.io.Serializable;

public class RuleModel implements Serializable {

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
