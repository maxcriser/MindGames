package com.example.mvmax.mindgames.gamecard.info.example;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ExampleMessageModel {

    @IntDef({DialogMessageType.INFO,
            DialogMessageType.PRESENTER,
            DialogMessageType.PLAYER,
            DialogMessageType.FINISH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DialogMessageType {

        int INFO = 0;
        int PRESENTER = 1;
        int PLAYER = 2;
        int FINISH = 3;
    }

    @DialogMessageType
    private int type;
    private String message;

    @DialogMessageType
    int getType() {
        return type;
    }

    String getMessage() {
        return message;
    }
}
