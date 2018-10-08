package com.example.mvmax.mindgames.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    public void onSkipAuth(final View view) {
        startMainActivity();
    }

    public void onGoogleAuth(final View view) {
        Toast.makeText(this, "Click Sign in with Google", Toast.LENGTH_LONG).show();

        processGoogleSignIn();
    }

    public void onFacebookAuth(final View view) {
        Toast.makeText(this, "Sign in with Facebook", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onGoogleSignedIn(final GoogleSignInAccount pAccount) {
        super.onGoogleSignedIn(pAccount);

        startMainActivity();
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
