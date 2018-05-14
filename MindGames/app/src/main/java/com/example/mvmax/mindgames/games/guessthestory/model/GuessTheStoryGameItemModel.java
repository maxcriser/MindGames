package com.example.mvmax.mindgames.games.guessthestory.model;

import java.io.Serializable;

public class GuessTheStoryGameItemModel implements Serializable {

    private String name;
    private String task;
    private String answer;
    private String uniqueId;
    private boolean isCompleted;

    public void setCompleted(final boolean pIsCompleted) {
        isCompleted = pIsCompleted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getTask() {
        return task;
    }

    public String getAnswer() {
        return answer;
    }
}