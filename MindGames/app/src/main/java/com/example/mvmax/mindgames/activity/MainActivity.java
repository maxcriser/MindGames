package com.example.mvmax.mindgames.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.drawer.AppDrawer;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);

        showCollectionFragment();
        initAppDrawer();
    }

    private void initAppDrawer() {
        final AppDrawer appDrawer = findViewById(R.id.main_drawer);

        appDrawer.setClickListener(appDrawer.getSignOutButton(), new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                processGoogleSignOut();
            }
        });
    }

    @Override
    protected void onGoogleSignedOut() {
        super.onGoogleSignedOut();

        startAuthActivity();
    }


    private void startAuthActivity() {
        startActivity(new Intent(this, AuthActivity.class));
    }
}