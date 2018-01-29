package com.example.mvmax.mindgames.drawer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.util.UiUtils;

public class AppDrawer extends RelativeLayout {

    public AppDrawer(final Context context) {
        super(context);
        init();
    }

    public AppDrawer(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AppDrawer(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_drawer, this);

        setStatusBarPadding();
    }

    private void init() {
        inflate();
    }

    private void init(final AttributeSet pAttrs) {
        inflate();
    }

    private void setStatusBarPadding() {
        final View rootView = findViewById(R.id.content_drawer);
        rootView.setPadding(0, UiUtils.getStatusBarHeight(getContext()), 0, 0);
    }
}