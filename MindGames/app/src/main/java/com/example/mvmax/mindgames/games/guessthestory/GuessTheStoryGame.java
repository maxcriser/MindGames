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
    public String getPosterUrl() {
        return "http://www.gamersyde.com/news_below_death_s_door_trailer-17684.jpg";
    }

    @Override
    public Class getActivityClass() {
        return GuessTheStoryGameActivity.class;
    }
}