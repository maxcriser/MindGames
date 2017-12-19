package com.example.mvmax.mindgames.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.util.UiUtils;

public class Toolbar extends RelativeLayout {

    private String mTitleString;
    private TextView mTitle;
    private AppCompatImageView mMenuIcon;

    public Toolbar(final Context context) {
        super(context);
        init();
    }

    public Toolbar(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Toolbar(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_toolbar, this);

        mTitle = findViewById(R.id.toolbar_view_title);
        mMenuIcon = findViewById(R.id.toolbar_view_menu);
    }

    private void init() {
        inflate();
    }

    private void init(final AttributeSet pAttrs) {
        inflate();

        final TypedArray typedArray = getContext().obtainStyledAttributes(pAttrs, R.styleable.custom_toolbar, 0, 0);

        try {
            mTitleString = typedArray.getString(R.styleable.custom_toolbar_title);
        } finally {
            typedArray.recycle();
        }

        updateView();
    }

    private void updateView() {
        updateTitle();
    }

    private void updateTitle() {
        UiUtils.setTextOrHide(mTitle, mTitleString);
    }

    private void setTitle(final String pString) {
        mTitleString = pString;

        updateTitle();
    }

    public AppCompatImageView getMenuIconView() {
        return mMenuIcon;
    }
}