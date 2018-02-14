package com.example.mvmax.mindgames.executable;

import java.io.Serializable;

public interface IExecute<T extends Serializable> {

    T execute();

}