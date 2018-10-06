package com.example.mvmax.mindgames.util;

import com.example.mvmax.mindgames.config.AppConfig;

import static com.example.mvmax.mindgames.config.AppConfig.isPremiumAccount;

public final class AppUtils {

    public static boolean isContentAvailable(final boolean pIsPaid) {
        if (pIsPaid) {
            return isPremiumAccount();
        } else {
            return true;
        }
    }

    public static boolean isBoughtContent(final boolean pIsPaid) {
        return AppConfig.isPremiumAccount() && pIsPaid;
    }
}