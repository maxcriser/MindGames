package com.example.mvmax.mindgames.games.guessthestory.model;

import android.support.annotation.Nullable;

import com.example.mvmax.mindgames.model.IModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GuessTheStoryGameModel implements IModel<GuessTheStoryGameItemModel>, Serializable {

    private final List<GuessTheStoryGameItemModel> stories;

    public GuessTheStoryGameModel() {
        stories = new ArrayList<>();
    }

    @Override
    public int size() {
        return stories.size();
    }

    @Override
    public void clear() {
        stories.clear();
    }

    @Override
    public boolean isEmpty() {
        return stories.isEmpty();
    }

    @Nullable
    @Override
    public List<GuessTheStoryGameItemModel> getList() {
        return stories;
    }
}