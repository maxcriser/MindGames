package com.example.mvmax.mindgames.executable;

import com.example.mvmax.mindgames.gamecard.model.GameCardModel;
import com.example.mvmax.mindgames.games.model.GamesModel;

public class GameByIdExecutable implements IExecute<GameCardModel> {

    private final String mID;

    public GameByIdExecutable(final String pID) {
        mID = pID;
    }

    @Override
    public GameCardModel execute() {
        final GamesModel gamesModel = new GamesExecutable().execute();

        return gamesModel.getGame(mID);
    }
}
