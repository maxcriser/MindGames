package com.example.mvmax.mindgames.gamecollection;

import com.example.mvmax.mindgames.games.IBaseGame;
import com.example.mvmax.mindgames.games.guessthestory.GuessTheStoryGame;
import com.example.mvmax.mindgames.games.guesstheword.GuessTheWordGame;

import java.util.ArrayList;
import java.util.List;

public class GameCollection {

    public static final List<IBaseGame> GAMES = new ArrayList<IBaseGame>() {{
        add(new GuessTheStoryGame());
        add(new GuessTheWordGame());
    }};

}
