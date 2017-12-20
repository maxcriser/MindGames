package com.example.mvmax.mindgames.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.game.GameFragment;
import com.example.mvmax.mindgames.model.GameCardModel;
import com.example.mvmax.mindgames.util.UiUtils;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
    }

    public void initDrawer() {

    }

    public void showGameFragment(final GameCardModel pGameCardModel) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(android.R.id.content, GameFragment.newInstance(pGameCardModel))
                .addToBackStack(null)
                .commit();
    }

    public void openDrawer() {
        final DrawerLayout mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.openDrawer(Gravity.START);
    }

    private void closeDrawer() {
        final DrawerLayout mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawer(Gravity.START);
    }

    public void setBackground(final Bitmap pBitmap) {
        final ImageView pBlurredImageView = findViewById(R.id.background_image);
        pBlurredImageView.setImageBitmap(pBitmap);
    }

    public void setBackground(final Drawable pDrawable) {
        final ImageView pBlurredImageView = findViewById(R.id.background_image);
        pBlurredImageView.setImageDrawable(pDrawable);
    }

    public void setStatusBarPadding() {
        final View contentView = findViewById(R.id.activity_base_content);

        contentView.setPadding(0, UiUtils.getStatusBarHeight(this), 0, 0);
    }
}