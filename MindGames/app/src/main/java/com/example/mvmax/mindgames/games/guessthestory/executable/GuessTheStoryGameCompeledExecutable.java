package com.example.mvmax.mindgames.games.guessthestory.executable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mvmax.mindgames.database.AppDataBaseHelper;
import com.example.mvmax.mindgames.database.DatabaseHolder;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameCompletedItemModel;

import java.util.ArrayList;
import java.util.List;

public class GuessTheStoryGameCompeledExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;

    public GuessTheStoryGameCompeledExecutable() {
        mAppDataBaseHelper = DatabaseHolder.get();
    }

    public List<String> execute() {
        final List<String> list = new ArrayList<>();

        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                GuessTheStoryGameCompletedItemModel.Field.ID,
                GuessTheStoryGameCompletedItemModel.Field.UNIQUE_ID
        };

        final Cursor cursor = db.query(
                GuessTheStoryGameCompletedItemModel.Field.TABLE,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String uniqueId = cursor.getString(cursor.getColumnIndexOrThrow(GuessTheStoryGameCompletedItemModel.Field.UNIQUE_ID));

            list.add(uniqueId);
        }

        cursor.close();
        db.close();

        return list;
    }
}