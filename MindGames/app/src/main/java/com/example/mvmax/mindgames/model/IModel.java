package com.example.mvmax.mindgames.model;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public interface IModel<T extends Serializable> {

    int size();

    void clear();

    boolean isEmpty();

    @Nullable
    List<T> getList();
}
