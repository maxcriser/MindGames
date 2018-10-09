package com.example.mvmax.mindgames.util;

import android.content.SharedPreferences;

import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.constants.Constant;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.EMAIL;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.SIGNED_IN_AS;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.URL_PHOTO;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.USERNAME;

public final class AuthUtils {

    public static void saveGoogleAccountData(final BaseActivity pBaseActivity, final GoogleSignInAccount pAccount) {
        final SharedPreferences.Editor editor = pBaseActivity.getSharedPref().edit();
        editor.putString(USERNAME, pAccount.getDisplayName());
        editor.putString(EMAIL, pAccount.getEmail());
        editor.putString(URL_PHOTO, String.valueOf(pAccount.getPhotoUrl()));
        editor.putString(SIGNED_IN_AS, Constant.SignIn.AS_GOOGLE);
        editor.apply();
    }

    public static void clearAccountData(final BaseActivity pBaseActivity) {
        final SharedPreferences.Editor editor = pBaseActivity.getSharedPref().edit();
        editor.putString(USERNAME, StringUtil.EMPTY);
        editor.putString(EMAIL, StringUtil.EMPTY);
        editor.putString(URL_PHOTO, StringUtil.EMPTY);
        editor.putString(SIGNED_IN_AS, StringUtil.EMPTY);
        editor.apply();
    }

    public static String getSignInType(final BaseActivity pBaseActivity) {
        return getSharedStringValue(SIGNED_IN_AS, pBaseActivity);
    }

    public static String getAccountPhotoUrl(final BaseActivity pBaseActivity) {
        return getSharedStringValue(URL_PHOTO, pBaseActivity);
    }

    public static String getAccountUsername(final BaseActivity pBaseActivity) {
        return getSharedStringValue(USERNAME, pBaseActivity);
    }

    public static String getAccountEmail(final BaseActivity pBaseActivity) {
        return getSharedStringValue(EMAIL, pBaseActivity);
    }

    private static String getSharedStringValue(final String pValue, final BaseActivity pBaseActivity) {
        final SharedPreferences sharedPref = pBaseActivity.getSharedPref();

        return sharedPref.getString(pValue, StringUtil.EMPTY);
    }
}
