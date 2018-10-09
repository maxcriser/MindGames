package com.example.mvmax.mindgames.clicklistener;

import android.view.View;
import android.widget.Toast;

import com.example.mvmax.mindgames.config.AppConfig;

public class BuyPremiumClickListener implements View.OnClickListener {

    @Override
    public void onClick(final View v) {
        if (AppConfig.isLoggedIn(v.getContext())) {
            Toast.makeText(v.getContext(), "You can start pay", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(v.getContext(), "You need to be authorized", Toast.LENGTH_LONG).show();
        }
    }
}
