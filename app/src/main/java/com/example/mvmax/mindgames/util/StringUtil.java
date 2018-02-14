package com.example.mvmax.mindgames.util;

public final class StringUtil {

    public static final String EMPTY = "";

    static boolean isEmpty(final String pString) {
        return !(pString != null && !pString.equals(EMPTY));
    }
}