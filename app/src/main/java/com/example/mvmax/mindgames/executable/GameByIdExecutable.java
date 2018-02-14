package com.example.mvmax.mindgames.executable;

import com.example.mvmax.mindgames.gamecollection.GameCollectionFragment;
import com.example.mvmax.mindgames.games.IBaseGame;

public class GameByIdExecutable implements IExecute<IBaseGame> {

    private final String mID;

    public GameByIdExecutable(final String pID) {
        mID = pID;
    }

    @Override
    public IBaseGame execute() {
        for (final IBaseGame baseGame : GameCollectionFragment.mGames) {
            if (baseGame.getID().equals(mID)) {
                return baseGame;
            }
        }

        return null;
    }
}
