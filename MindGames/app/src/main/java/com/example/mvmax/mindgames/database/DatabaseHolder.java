package com.example.mvmax.mindgames.database;

public enum DatabaseHolder {

    INSTANCE;

    private AppDataBaseHelper mAppDataBaseHelper;

    public static AppDataBaseHelper get() {
        return INSTANCE.mAppDataBaseHelper;
    }

    public static void set(final AppDataBaseHelper pAppDataBaseHelper) {
        INSTANCE.mAppDataBaseHelper = pAppDataBaseHelper;
    }

}