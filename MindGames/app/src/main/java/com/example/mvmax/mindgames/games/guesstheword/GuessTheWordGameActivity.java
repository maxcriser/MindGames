package com.example.mvmax.mindgames.games.guesstheword;

import android.os.Bundle;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.clicklistener.OnBackClickListener;
import com.example.mvmax.mindgames.toolbar.Toolbar;

public class GuessTheWordGameActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_word_game);

//        final GuessTheWordGameModel gameModel = new GuessTheWordGameExecutable().execute();

        final Toolbar toolbar = findToolbarView();

        if (toolbar != null) {
            toolbar.setAllCaps(true);
//            toolbar.setTitle(gameModel.getTitle());
            toolbar.setTitle("Word game");
            toolbar.getbackIconView().setOnClickListener(new OnBackClickListener(this));
        }
    }
}