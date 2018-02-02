package com.example.mvmax.mindgames.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.base.BaseActivity;
import com.example.mvmax.mindgames.gamecollection.listener.IGameCollectionListener;

public class TestActivity extends BaseActivity {

    public void onTapClick(final View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_test);
    }
}