package com.example.mvmax.mindgames.games.guessthestory;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.base.BaseActivity;
import com.example.mvmax.mindgames.executable.GamesExecutable;

@SuppressLint("Registered")
public class GuessTheStoryGameActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_guess_the_story_game);

        new GamesExecutable().execute();
    }
}