package com.example.mvmax.mindgames.executable;

import com.example.mvmax.mindgames.constants.Constant;
import com.example.mvmax.mindgames.games.model.GamesModel;
import com.example.mvmax.mindgames.util.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GamesExecutable implements IExecute<GamesModel> {

    @Override
    public GamesModel execute() {
        final String gamesJson = FileUtil.readFromAsset(Constant.FilePath.GAMES_PATH);
        final Gson gamesGson = new GsonBuilder().create();

        return gamesGson.fromJson(gamesJson, GamesModel.class);
    }
}