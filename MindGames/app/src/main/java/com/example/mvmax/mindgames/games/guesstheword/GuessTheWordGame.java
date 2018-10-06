package com.example.mvmax.mindgames.games.guesstheword;

import com.example.mvmax.mindgames.R;
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
    public int getPosterIntDrawable() {
        return R.drawable.poster_guess_the_word;
    }

    @Override
    public Class getActivityClass() {
        return GuessTheWordGameActivity.class;
    }
}