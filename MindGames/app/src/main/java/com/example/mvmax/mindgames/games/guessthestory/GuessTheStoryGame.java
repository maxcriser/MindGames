package com.example.mvmax.mindgames.games.guessthestory;

import com.example.mvmax.mindgames.constants.Constant;
import com.example.mvmax.mindgames.games.AbstractBaseGame;

public class GuessTheStoryGame extends AbstractBaseGame {

    public GuessTheStoryGame() {
        super();
    }

    @Override
    public String getID() {
        return Constant.GameID.GUESS_THE_STORY;
    }

    @Override
    public String getPosterUrl() {
        return "https://avatars.mds.yandex.net/get-pdb/368827/f78dbaf4-f775-45f3-9796-62e4b74952b5/orig";
    }

    @Override
    public Class getActivityClass() {
        return GuessTheStoryGameActivity.class;
    }
}