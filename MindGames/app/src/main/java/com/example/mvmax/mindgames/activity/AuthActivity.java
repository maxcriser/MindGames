package com.example.mvmax.mindgames.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.Arrays;

public class AuthActivity extends BaseActivity {

    private static final String EMAIL = "email";

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(final FacebookException exception) {
                // App code
            }
        });

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
