package com.example.mvmax.mindgames.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.util.UiUtils;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
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
        final View contentView = findViewById(R.id.content);

        contentView.setPadding(0, UiUtils.getStatusBarHeight(this), 0, 0);
    }
}