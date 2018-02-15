package com.example.mvmax.mindgames.util;

import com.example.mvmax.mindgames.ContextHolder;

import java.io.IOException;
import java.io.InputStream;

public final class FileUtil {

    public static String readFromAsset(final String pFileName) {
        String result = StringUtil.EMPTY;

        try {
            final InputStream inputStream = ContextHolder.get().getAssets().open(pFileName);
            final int size = inputStream.available();
            final byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            result = new String(buffer);
        } catch (final IOException e) {
            Log.xe(FileUtil.class, e.toString());
        }

        return result;
    }
}
