package com.example.mvmax.mindgames.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;

public class AuthActivity extends BaseActivity {

    public void onSkipAuth(final View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onGoogleAuth(final View view) {
        Toast.makeText(this, "Sign in with Google", Toast.LENGTH_LONG).show();
    }

    public void onFacebookAuth(final View view) {
        Toast.makeText(this, "Sign in with Facebook", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

    }
}
