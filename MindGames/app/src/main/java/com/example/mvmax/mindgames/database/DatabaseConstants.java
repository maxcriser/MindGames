package com.example.mvmax.mindgames.database;

import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameCompletedItemModel;

public class DatabaseConstants {

    public interface Info {

        int DATABASE_VERSION = 1;

        String DATABASE_NAME = "mindgames.db";
    }

    public interface SQL {

        String SQL_CREATE_GTSG_COMPLETED_ENTRIES =
                "CREATE TABLE " + GuessTheStoryGameCompletedItemModel.Field.TABLE + " (" +
                        GuessTheStoryGameCompletedItemModel.Field.ID + " INTEGER PRIMARY KEY," +
                        GuessTheStoryGameCompletedItemModel.Field.UNIQUE_ID + " TEXT)";

        String SQL_DELETE_GTSG_COMPLETED_ENTRIES =
                "DROP TABLE IF EXISTS " + GuessTheStoryGameCompletedItemModel.Field.TABLE;
    }
}