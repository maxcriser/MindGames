package com.example.mvmax.mindgames.games.guessthestory.executable;

import com.example.mvmax.mindgames.constants.Constant;
import com.example.mvmax.mindgames.executable.IExecute;
import com.example.mvmax.mindgames.executable.IGameModelExecute;
import com.example.mvmax.mindgames.games.IBaseGame;
import com.example.mvmax.mindgames.games.guessthestory.GuessTheStoryGame;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameModel;
import com.example.mvmax.mindgames.util.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GuessTheStoryGameExecutable implements IExecute<GuessTheStoryGameModel> {

    @Override
    public GuessTheStoryGameModel execute() {
        final String storiesJson = FileUtil.readFromAsset(Constant.GuessTheStory.STORIES_PATH);
        final Gson storiesGson = new GsonBuilder().create();

        return storiesGson.fromJson(storiesJson, GuessTheStoryGameModel.class);
    }
}