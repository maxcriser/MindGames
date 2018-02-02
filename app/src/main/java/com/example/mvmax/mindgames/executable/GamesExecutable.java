package com.example.mvmax.mindgames.executable;

import com.example.mvmax.mindgames.constants.Constant;
import com.example.mvmax.mindgames.test.GamesModel;
import com.example.mvmax.mindgames.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GamesExecutable implements IExecute<GamesModel> {

    @Override
    public GamesModel execute() {
        final String games = FileUtils.readFromAsset(Constant.FilePath.GAMES);
        final Gson gson = new GsonBuilder().create();

        return gson.fromJson(games, GamesModel.class);
    }
}