package com.example.mvmax.mindgames.activity.base;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.AuthActivity;
import com.example.mvmax.mindgames.constants.Constant;
import com.example.mvmax.mindgames.gamecard.GameCardActivity;
import com.example.mvmax.mindgames.gamecollection.GameCollectionFragment;
import com.example.mvmax.mindgames.games.IBaseGame;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.util.AuthUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import tyrantgit.explosionfield.ExplosionField;

import static com.example.mvmax.mindgames.constants.Constant.GoogleAuth.RC_SIGN_IN;
import static com.example.mvmax.mindgames.constants.Constant.GoogleAuth.SIGNED_IN;
import static com.example.mvmax.mindgames.constants.Constant.GoogleAuth.STATE_IN_PROGRESS;
import static com.example.mvmax.mindgames.constants.Constant.GoogleAuth.STATE_SIGNING_IN;
import static com.example.mvmax.mindgames.constants.Constant.SharedPreferences.SHARED_PREF_NAME;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private int mSignInProgress;
    private PendingIntent mSignInIntent;

    private final int OUR_REQUEST_CODE = 1001;

    public static final String BASE_GAME_MODEL_TO_NEXT_ACTIVITY = "BASE_GAME_MODEL_TO_NEXT_ACTIVITY";
    private ExplosionField mExplosionField;

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        mGoogleApiClient = buildGoogleApiClient();

        mExplosionField = ExplosionField.attach2Window(this);
    }

    public void explodeView(final View pView) {
        mExplosionField.explode(pView);
    }

    public ImageView getBackgroundImageView() {
        return findViewById(R.id.background_image);
    }

    public void showCollectionFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_content, GameCollectionFragment.newInstance())
                .commit();
    }

    public void showGameActivity(final String pID) {
        final Intent intent = new Intent(this, GameCardActivity.class);
        intent.putExtra(GameCardActivity.EXTRA_GAME_ID, pID);

        startActivity(intent);
    }

    public void disableDrawer() {
        getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void enableDrawer() {
        getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void openDrawer() {
        getDrawerLayout().openDrawer(Gravity.START);
    }

    private void closeDrawer() {
        getDrawerLayout().closeDrawer(Gravity.START);
    }

    private DrawerLayout getDrawerLayout() {
        return findViewById(R.id.drawer_layout);
    }

    public void setBackgroundDrawable(final int pPoster) {
        final ImageView backgroundImageView = getBackgroundImageView();

        Picasso.with(this)
                .load(pPoster)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(backgroundImageView.getDrawable())
                .into(backgroundImageView);
    }

    public void setBackgroundUrl(final int pIntDrawable) {
        final ImageView backgroundImageView = getBackgroundImageView();

        Picasso.with(this)
                .load(pIntDrawable)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(backgroundImageView.getDrawable())
                .into(backgroundImageView);
    }

    public void openGameActivity(@NonNull final Class pActivityClass, final IBaseGame pBaseGame) {
        final Intent intent = new Intent(this, pActivityClass);
        final Bundle bundle = new Bundle();

        bundle.putSerializable(BASE_GAME_MODEL_TO_NEXT_ACTIVITY, pBaseGame);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Nullable
    public Toolbar findToolbarView() {
        return findViewById(R.id.toolbar_view);
    }

    protected int getActionBarSize() {
        return 100;
//        final TypedValue typedValue = new TypedValue();
//        final int[] textSizeAttr = new int[]{R.attr.actionBarSize};
//        final int indexOfAttrTextSize = 0;
//        final TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
//        final int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
//
//        a.recycle();
//
//        return actionBarSize;
    }

    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }

    public void loadGameCardPoster(final int pIntDrawable) {
        Picasso.with(this).load(pIntDrawable).into((ImageView) findViewById(R.id.game_fragment_poster));
    }

    public void loadGameCardHeader(final int pIntDrawable) {
        Picasso.with(this).load(pIntDrawable).into((ImageView) findViewById(R.id.game_fragment_header_background));
    }

    @Override
    public void onConnected(@Nullable final Bundle pBundle) {
        mSignInProgress = SIGNED_IN;
        processGetGoogleAccountInfo();
    }

    private void processGetGoogleAccountInfo() {
        final OptionalPendingResult opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

        opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {

            @Override
            public void onResult(@NonNull final GoogleSignInResult result) {
                if (result.isSuccess()) {
                    try {
                        final GoogleSignInAccount account = result.getSignInAccount();

                        onGoogleSignedIn(account);
                    } catch (final Exception ex) {
                        final String exception = ex.getLocalizedMessage();
                        final String exceptionString = ex.toString();
                    }
                }
            }
        });
    }

    @Override
    public void onConnectionSuspended(final int pI) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult pConnectionResult) {
        if (mSignInProgress != STATE_IN_PROGRESS) {
            mSignInIntent = pConnectionResult.getResolution();
            if (mSignInProgress == STATE_SIGNING_IN) {
                resolveSignInError(pConnectionResult);
            }
        }

        onSignedOut();
    }

    private void resolveSignInError(final ConnectionResult pConnectionResult) {
        if (mSignInIntent != null) {
            try {
                mSignInProgress = STATE_IN_PROGRESS;
                pConnectionResult.startResolutionForResult(this, OUR_REQUEST_CODE);
            } catch (final IntentSender.SendIntentException e) {
                mSignInProgress = STATE_SIGNING_IN;
                mGoogleApiClient.connect();
            }
        } else {
            // You have a play services error -- inform the user
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode == RESULT_OK) {
                    mSignInProgress = STATE_SIGNING_IN;

                    processGetGoogleAccountInfo();
                } else {
                    mSignInProgress = SIGNED_IN;
                }

                if (!mGoogleApiClient.isConnecting()) {
                    mGoogleApiClient.connect();
                }
                break;
        }
    }

    private void onSignedOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {

                    @Override
                    public void onResult(@NonNull final Status status) {
                        onGoogleSignedOut();
                    }
                });

    }

    private GoogleApiClient buildGoogleApiClient() {
        final GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        return new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();

        super.onStop();
    }

    public void processGoogleSignIn() {
        final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.sign_out_header)
                .setMessage(R.string.sign_out_description)
                .setCancelable(false)
                .setPositiveButton(R.string.sign_out_button, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        processSignOut();
                    }
                })
                .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {

                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void processSignOut() {
        final String signInType = AuthUtils.getSignInType(this);

        switch (signInType) {
            case Constant.SignIn.AS_GOOGLE:
                processGoogleSignOut();

                break;
            case Constant.SignIn.AS_FACEBOOK:

                break;

            default:
                break;
        }
    }

    public void processGoogleSignOut() {
        AuthUtils.clearAccountData(this);
        onSignedOut();
        mGoogleApiClient.disconnect();
        mGoogleApiClient.connect();

        startAuthActivity();
    }

    public void startAuthActivity() {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void processGoogleRevokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient);
        mGoogleApiClient = buildGoogleApiClient();
        mGoogleApiClient.connect();

        onGoogleRevokeAccess();
    }

    public SharedPreferences getSharedPref() {
        return getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }

    protected void onGoogleSignedIn(final GoogleSignInAccount pAccount) {
        AuthUtils.saveGoogleAccountData(this, pAccount);

        Toast.makeText(this, String.format("Signed In to My App as %s", pAccount.getEmail()), Toast.LENGTH_LONG).show();
    }

    protected void onGoogleSignedOut() {
        AuthUtils.clearAccountData(this);

        Toast.makeText(this, "Google signed out", Toast.LENGTH_LONG).show();
    }

    protected void onGoogleRevokeAccess() {
        Toast.makeText(this, "Google revoke access", Toast.LENGTH_LONG).show();
    }

}