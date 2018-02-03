package com.example.mvmax.mindgames.model;

import android.support.annotation.Nullable;

import java.util.List;

public interface IModel<T> {

    int size();

    void clear();

    boolean isEmpty();

    @Nullable
    List<T> getList();

}
