package com.example.mvmax.mindgames;

import android.app.Application;

import com.example.mvmax.mindgames.database.AppDataBaseHelper;
import com.example.mvmax.mindgames.database.DatabaseHolder;

public class Core extends Application {

    public Core() {
        ContextHolder.set(this);
        DatabaseHolder.set(new AppDataBaseHelper(this));
    }
}