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
        return "http://www.modafinilsale.com/data/out/237/228441440-dishonored-wallpaper.jpg";
    }

    @Override
    public Class getActivityClass() {
        return GuessTheWordGameActivity.class;
    }
}