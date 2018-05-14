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
        return "https://pre00.deviantart.net/b59b/th/pre/i/2015/064/f/1/metroid_prime_by_noble__6-d8kl4mp.jpg";
    }

    @Override
    public Class getActivityClass() {
        return GuessTheStoryGameActivity.class;
    }
}