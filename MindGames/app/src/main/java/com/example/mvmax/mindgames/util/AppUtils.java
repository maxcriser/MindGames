package com.example.mvmax.mindgames.util;

import android.content.Context;

import com.example.mvmax.mindgames.config.AppConfig;

import static com.example.mvmax.mindgames.config.AppConfig.isPremiumAccount;

public final class AppUtils {

    public static boolean isContentAvailable(final Context pContext, final boolean pIsPaid) {
        if (pIsPaid) {
            return isPremiumAccount(pContext);
        } else {
            return true;
        }
    }

    public static boolean isBoughtContent(final Context pContext, final boolean pIsPaid) {
        return AppConfig.isPremiumAccount(pContext) && pIsPaid;
    }
}