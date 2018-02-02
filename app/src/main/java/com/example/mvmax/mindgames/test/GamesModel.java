package com.example.mvmax.mindgames.test;

import android.support.annotation.Nullable;

import com.example.mvmax.mindgames.games.IModel;

import java.util.ArrayList;
import java.util.List;

public class GamesModel implements IModel<GameCard> {

    private final List<GameCard> games;

    public GamesModel() {
        games = new ArrayList<>();
    }

    public GameCard getGame(final String pID) {
        for (int position = 0; position < games.size(); position++) {
            final GameCard gameCard = games.get(position);

            if (gameCard != null && gameCard.getId().equalsIgnoreCase(pID)) {
                return gameCard;
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
    public List<GameCard> getList() {
        return games;
    }
}