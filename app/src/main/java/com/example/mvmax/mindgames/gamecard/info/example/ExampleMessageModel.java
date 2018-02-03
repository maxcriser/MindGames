package com.example.mvmax.mindgames.gamecard.info.example;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ExampleMessageModel {

    @StringDef({DialogMessageType.INFO,
            DialogMessageType.PRESENTER,
            DialogMessageType.PLAYER,
            DialogMessageType.FINISH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DialogMessageType {

        String INFO = "info";
        String PRESENTER = "presenter";
        String PLAYER = "player";
        String FINISH = "finish";
    }

    @DialogMessageType
    private String type;
    private String message;

    @DialogMessageType
    String getType() {
        return type;
    }

    String getMessage() {
        return message;
    }
}