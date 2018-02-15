package com.example.mvmax.mindgames.clicklistener;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class OnBackClickListener implements View.OnClickListener {

    private final Context mContext;

    public OnBackClickListener(final Context pContext) {
        mContext = pContext;
    }

    @Override
    public void onClick(final View pView) {
        ((Activity) mContext).onBackPressed();
    }
}