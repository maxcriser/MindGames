package com.example.mvmax.mindgames.drawer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.config.AppConfig;
import com.example.mvmax.mindgames.util.AuthUtils;
import com.example.mvmax.mindgames.util.StringUtil;
import com.squareup.picasso.Picasso;

public class AppDrawer extends RelativeLayout {

    DrawerButton mSignOutButton;
    DrawerButton mSignInButton;

    public AppDrawer(final Context pContext) {
        super(pContext);
        init();
    }

    public AppDrawer(final Context pContext, final AttributeSet pAttrs) {
        super(pContext, pAttrs);
        init(pAttrs);
    }

    public AppDrawer(final Context pContext, final AttributeSet pAttrs, final int pDefStyle) {
        super(pContext, pAttrs, pDefStyle);
        init(pAttrs);
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_drawer, this);

        initViews();
    }

    private void init() {
        inflate();
    }

    private void init(final AttributeSet pAttrs) {
        inflate();
    }

    private void initViews() {
        mSignOutButton = findViewById(R.id.drawer_button_sign_out);
        mSignInButton = findViewById(R.id.drawer_button_sign_in);

        updateUIAccountGoogle();

        mSignInButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                ((BaseActivity) v.getContext()).signOut();
            }
        });

        mSignOutButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                ((BaseActivity) v.getContext()).signOut();

            }
        });
    }

    public void setClickListener(final View pView, final OnClickListener pOnClickListener) {
        pView.setOnClickListener(pOnClickListener);
    }

    public View getSignOutButton() {
        return mSignOutButton;
    }

    public View getSignInButton() {
        return mSignInButton;
    }

    public void updateUIAccountGoogle() {
        final ImageView profilePhoto = findViewById(R.id.profile_photo);
        final ImageView appLogo = findViewById(R.id.app_logo);
        final Context context = getContext();
        final TextView userName = findViewById(R.id.drawer_username);
        final TextView email = findViewById(R.id.drawer_email);

        if (AppConfig.isLoggedIn(context)) {
            appLogo.setVisibility(View.GONE);
            getSignInButton().setVisibility(View.GONE);
            getSignOutButton().setVisibility(View.VISIBLE);
            profilePhoto.setVisibility(View.VISIBLE);

            userName.setText(AuthUtils.getAccountUsername(context));
            email.setText(AuthUtils.getAccountEmail(context));

            final String photoUri = AuthUtils.getAccountPhotoUrl(context);

            if (StringUtil.isNotEmpty(photoUri)) {
                Picasso.with(context)
                        .load(AuthUtils.getAccountPhotoUrl(context))
                        .placeholder(R.drawable.no_photo_icon)
                        .error(R.drawable.no_photo_icon)
                        .into(profilePhoto);
            } else {
                profilePhoto.setImageDrawable(context.getResources().getDrawable(R.drawable.no_photo_icon));
            }
        } else {
            appLogo.setVisibility(View.VISIBLE);
            getSignInButton().setVisibility(View.VISIBLE);
            getSignOutButton().setVisibility(View.GONE);
            profilePhoto.setVisibility(View.GONE);
            userName.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
        }
    }
}