package com.example.mvmax.mindgames.gamecollection.listener;

import java.io.Serializable;

public interface IGameCollectionListener extends Serializable {

    void onPageSelected(final int pPoster);

    void onResume();

    void onPause();

}
