package com.example.mvmax.mindgames.util;

import java.util.Collection;

public final class ListUtil {

    public static boolean isEmpty(final Collection pArrayList) {
        return !(pArrayList != null && !pArrayList.isEmpty());
    }
}
