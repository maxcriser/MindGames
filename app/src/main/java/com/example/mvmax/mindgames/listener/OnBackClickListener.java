package com.example.mvmax.mindgames.listener;

import android.content.Context;
import android.view.View;

import com.example.mvmax.mindgames.activity.BaseActivity;

public class OnBackClickListener implements View.OnClickListener {

    private final Context mContext;

    public OnBackClickListener(final Context pContext) {
        mContext = pContext;
    }

    @Override
    public void onClick(final View pView) {
        ((BaseActivity) mContext).onBackPressed();
    }
}