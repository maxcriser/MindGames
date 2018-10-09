package com.example.mvmax.mindgames.config;

import android.content.Context;

import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.constants.Constant;
import com.example.mvmax.mindgames.util.AuthUtils;

public final class AppConfig {

    public static boolean isPremiumAccount(final Context pContext) {
        return isLoggedIn(pContext) && false;
    }

    public static boolean isLoggedIn(final Context pContext) {
        return !AuthUtils.getSignInType((BaseActivity) pContext).equals(Constant.SignIn.ANONYMOUS);
    }
}