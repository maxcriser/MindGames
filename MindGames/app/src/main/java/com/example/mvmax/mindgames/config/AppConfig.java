package com.example.mvmax.mindgames.config;

public final class AppConfig {

    public static boolean isPremiumAccount() {
        return isLoggedIn() && true;
    }

    public static boolean isLoggedIn() {
        return true;
    }
}