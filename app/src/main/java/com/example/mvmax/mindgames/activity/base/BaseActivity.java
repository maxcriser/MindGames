package com.example.mvmax.mindgames.activity.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ImageView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.gamecard.GameCardFragment;
import com.example.mvmax.mindgames.gamecollection.GameCollectionFragment;
import com.example.mvmax.mindgames.gamecollection.listener.IGameCollectionListener;
import com.example.mvmax.mindgames.games.IBaseGame;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
    }

    public ImageView getBackgroundImageView() {
        return findViewById(R.id.background_image);
    }

    public void showCollectionFragment(final IGameCollectionListener pGameCollectionListener) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.activity_main_content, GameCollectionFragment.newInstance(pGameCollectionListener))
                .addToBackStack(null)
                .commit();
    }

    public void showGameFragment(final IBaseGame pBaseGame) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.activity_main_content, GameCardFragment.newInstance(pBaseGame))
                .addToBackStack(null)
                .commit();
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

    public void setBackground(final int pPoster) {
        final ImageView backgroundImageView = getBackgroundImageView();

        Picasso.with(this)
                .load(pPoster)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(backgroundImageView.getDrawable())
                .into(backgroundImageView);
    }
}