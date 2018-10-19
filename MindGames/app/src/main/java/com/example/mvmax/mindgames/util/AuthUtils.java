package com.example.mvmax.mindgames.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mvmax.mindgames.constants.Constant;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.EMAIL;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.SHARED_PREF_NAME;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.SIGNED_IN_AS;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.URL_PHOTO;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.USERNAME;

public final class AuthUtils {

    public static void saveGoogleAccountData(final Context pContext, final GoogleSignInAccount pAccount) {
        final SharedPreferences.Editor editor = getSharedPref(pContext).edit();
        editor.putString(USERNAME, pAccount.getDisplayName());
        editor.putString(EMAIL, pAccount.getEmail());
        editor.putString(URL_PHOTO, String.valueOf(pAccount.getPhotoUrl()));
        editor.putString(SIGNED_IN_AS, Constant.SignIn.AS_GOOGLE);
        editor.apply();
    }

    public static void clearAccountData(final Context pContext) {
        final SharedPreferences.Editor editor = getSharedPref(pContext).edit();
        editor.putString(USERNAME, StringUtil.EMPTY);
        editor.putString(EMAIL, StringUtil.EMPTY);
        editor.putString(URL_PHOTO, StringUtil.EMPTY);
        editor.putString(SIGNED_IN_AS, StringUtil.EMPTY);
        editor.apply();
    }

    public static String getSignInType(final Context pContext) {
        return getSharedStringValue(SIGNED_IN_AS, pContext);
    }

    public static String getAccountPhotoUrl(final Context pContext) {
        return getSharedStringValue(URL_PHOTO, pContext);
    }

    public static String getAccountUsername(final Context pContext) {
        return getSharedStringValue(USERNAME, pContext);
    }

    public static String getAccountEmail(final Context pContext) {
        return getSharedStringValue(EMAIL, pContext);
    }

    private static String getSharedStringValue(final String pValue, final Context pContext) {
        final SharedPreferences sharedPref = getSharedPref(pContext);

        return sharedPref.getString(pValue, StringUtil.EMPTY);
    }

    private static SharedPreferences getSharedPref(final Context pContext) {
        return pContext.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }
}
