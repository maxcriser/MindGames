package com.example.mvmax.mindgames.test;

import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;

import java.util.ArrayList;
import java.util.List;

public class Examples {

    final List<ExampleMessageModel> example;

    Examples() {
        example = new ArrayList<>();
    }

    public List<ExampleMessageModel> getList() {
        return example;
    }

}
