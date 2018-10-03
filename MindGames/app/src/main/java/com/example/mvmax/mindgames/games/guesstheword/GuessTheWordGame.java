package com.example.mvmax.mindgames.games.guesstheword;

import com.example.mvmax.mindgames.constants.Constant;
import com.example.mvmax.mindgames.games.AbstractBaseGame;

public class GuessTheWordGame extends AbstractBaseGame {

    public GuessTheWordGame() {
        super();
    }

    public String getID() {
        return Constant.GameID.GUESS_THE_WORD;
    }

    @Override
    public String getPosterUrl() {
        return "https://www.problemgambling.sa.gov.au/__data/assets/image/0018/52470/WLLR-LCD-1080x1920.jpg";
    }

    @Override
    public Class getActivityClass() {
        return GuessTheWordGameActivity.class;
    }
}