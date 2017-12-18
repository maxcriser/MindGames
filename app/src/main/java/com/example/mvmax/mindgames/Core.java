package com.example.mvmax.mindgames;

import android.app.Application;

public class Core extends Application {

    public Core() {
        ContextHolder.set(this);
    }
}