package com.example.mvmax.mindgames.util;

final class StringUtils {

    private static final String EMPTY = "";

    static boolean isEmpty(final String pString) {
        return !(pString != null && !pString.equals(EMPTY));
    }
}