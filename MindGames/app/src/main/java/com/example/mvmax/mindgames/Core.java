package com.example.mvmax.mindgames;

import android.support.multidex.MultiDexApplication;

import com.example.mvmax.mindgames.database.AppDataBaseHelper;
import com.example.mvmax.mindgames.database.DatabaseHolder;

public class Core extends MultiDexApplication {

    public Core() {
        ContextHolder.set(this);
        DatabaseHolder.set(new AppDataBaseHelper(this));
    }
}