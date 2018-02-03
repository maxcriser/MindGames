package com.example.mvmax.mindgames.games.model;

import android.support.annotation.Nullable;

import com.example.mvmax.mindgames.model.IModel;
import com.example.mvmax.mindgames.gamecard.model.GameCardModel;

import java.util.ArrayList;
import java.util.List;

public class GamesModel implements IModel<GameCardModel> {

    private final List<GameCardModel> games;

    public GamesModel() {
        games = new ArrayList<>();
    }

    public GameCardModel getGame(final String pID) {
        for (int position = 0; position < games.size(); position++) {
            final GameCardModel gameCardModel = games.get(position);

            if (gameCardModel != null && gameCardModel.getId().equalsIgnoreCase(pID)) {
                return gameCardModel;
            }
        }

        return null;
    }

    @Override
    public int size() {
        return games.size();
    }

    @Override
    public void clear() {
        games.clear();
    }

    @Override
    public boolean isEmpty() {
        return games.isEmpty();
    }

    @Nullable
    @Override
    public List<GameCardModel> getList() {
        return games;
    }
}