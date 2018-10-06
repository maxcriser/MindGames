package com.example.mvmax.mindgames.games.guessthestory;

import com.example.mvmax.mindgames.R;
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
    public int getPosterIntDrawable() {
        return R.drawable.poster_guess_the_story;
    }

    @Override
    public Class getActivityClass() {
        return GuessTheStoryGameActivity.class;
    }
}