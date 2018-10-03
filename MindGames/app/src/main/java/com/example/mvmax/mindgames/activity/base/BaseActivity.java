package com.example.mvmax.mindgames.activity.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.gamecard.GameCardActivity;
import com.example.mvmax.mindgames.gamecollection.GameCollectionFragment;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.util.UiUtil;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import tyrantgit.explosionfield.ExplosionField;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private ExplosionField mExplosionField;

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

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
                .addToBackStack(null)
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

    public void setBackgroundUrl(final String pUrl) {
        final ImageView backgroundImageView = getBackgroundImageView();

        Picasso.with(this)
                .load(pUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(backgroundImageView.getDrawable())
                .into(backgroundImageView);
    }

    public void openGameActivity(final Context pContext, @NonNull final Class pActivityClass) {
        startActivity(new Intent(pContext, pActivityClass));
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

    public void loadGameCardPoster(final String pUrl) {
        Picasso.with(this).load(pUrl).into((ImageView) findViewById(R.id.game_fragment_poster));
    }

    public void loadGameCardHeader(final String pUrl) {
        Picasso.with(this).load(pUrl).into((ImageView) findViewById(R.id.game_fragment_header_background));
    }
}