package com.example.mvmax.mindgames.executable;

import com.example.mvmax.mindgames.test.GameCard;
import com.example.mvmax.mindgames.test.GamesModel;

public class GameByIdExecutable implements IExecute<GameCard> {

    private final String mID;

    public GameByIdExecutable(final String pID) {
        mID = pID;
    }

    @Override
    public GameCard execute() {
        final GamesModel gamesModel = new GamesExecutable().execute();

        return gamesModel.getGame(mID);
    }
}
