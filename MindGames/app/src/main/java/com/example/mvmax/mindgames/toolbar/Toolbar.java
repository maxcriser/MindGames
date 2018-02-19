package com.example.mvmax.mindgames.toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.util.UiUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Toolbar extends RelativeLayout {

    private View mContainer;
    private View mSecondBackground;
    private TextView mTitle;
    private AppCompatImageView mMenuIcon;
    private AppCompatImageView mBackIcon;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Action.NONE,
            Action.MENU,
            Action.BACK})
    public @interface Action {

        int NONE = 0;
        int MENU = 1;
        int BACK = 2;
    }

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

    private boolean containsFlag(final int flagSet, final int flag) {
        final int result = flagSet | flag;

        return result == flagSet;
    }

    private void initActions(final int pActions) {
        if (pActions > Action.NONE) {
            mMenuIcon.setVisibility(containsFlag(pActions, Action.MENU) ? View.VISIBLE : View.GONE);
            mBackIcon.setVisibility(containsFlag(pActions, Action.BACK) ? View.VISIBLE : View.GONE);
        }
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_toolbar, this);

        mTitle = findViewById(R.id.toolbar_view_title);
        mContainer = findViewById(R.id.toolbar_view_container);
        mMenuIcon = findViewById(R.id.toolbar_view_menu);
        mBackIcon = findViewById(R.id.toolbar_view_back);
        mSecondBackground = findViewById(R.id.second_background_toolbar_view);
    }

    private void init() {
        inflate();
    }

    private void init(final AttributeSet pAttrs) {
        inflate();

        @SuppressLint("CustomViewStyleable") final TypedArray typedArray = getContext().obtainStyledAttributes(pAttrs, R.styleable.mg_toolbar, 0, 0);

        try {
            final int secondBackgroundColor = typedArray.getColor(R.styleable.mg_toolbar_second_background_color, Color.TRANSPARENT);
            final int color = typedArray.getColor(R.styleable.mg_toolbar_background_color, Color.TRANSPARENT);
            final String titleString = typedArray.getString(R.styleable.mg_toolbar_title);
            final int actions = typedArray.getInteger(R.styleable.mg_toolbar_action, Action.NONE);

            updateView(actions);
            setToolbarBackground(color);
            setTitle(titleString);
            setSecondBackgroundColor(secondBackgroundColor);
        } finally {
            typedArray.recycle();
        }
    }

    private void setSecondBackgroundColor(final int pColor) {
        mSecondBackground.setBackgroundColor(pColor);
    }

    public View getSecondBackgroundView() {
        return mSecondBackground;
    }

    private void updateView(final int pActions) {
        initActions(pActions);
    }

    public void setTitle(final String pString) {
        UiUtil.setTextOrHide(mTitle, pString);
    }

    public void setAllCaps(final boolean pIsAllCaps) {
        mTitle.setAllCaps(pIsAllCaps);
    }

    public void setToolbarBackground(final int pToolbarBackground) {
        mContainer.setBackgroundColor(pToolbarBackground);
    }

    public void addStatusBarHeight(final int pPx) {
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mContainer.getLayoutParams();
        layoutParams.setMargins(0, pPx, 0, 0);
    }

    public AppCompatImageView getMenuIconView() {
        return mMenuIcon;
    }

    public AppCompatImageView getbackIconView() {
        return mBackIcon;
    }
}