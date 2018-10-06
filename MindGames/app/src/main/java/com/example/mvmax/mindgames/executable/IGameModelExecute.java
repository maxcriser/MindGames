package com.example.mvmax.mindgames.executable;

import java.io.Serializable;

public interface IGameModelExecute<T extends Serializable> {

    T merge(final T pModel);

}