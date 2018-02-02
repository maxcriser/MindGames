package com.example.mvmax.mindgames.util;

import com.example.mvmax.mindgames.BuildConfig;

final class Log {

    static void xe(final Class pTag, final String pMessage) {
        xe(pTag.getSimpleName(), pMessage);
    }

    static void xe(final String pTag, final String pMessage) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(pTag, pMessage);
        }
    }
}
