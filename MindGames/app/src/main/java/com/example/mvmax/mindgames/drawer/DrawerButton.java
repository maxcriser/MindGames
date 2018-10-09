package com.example.mvmax.mindgames.drawer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;

public class DrawerButton extends RelativeLayout {

    private TextView mButtonView;

    public DrawerButton(final Context pContext) {
        super(pContext);
        init();
    }

    public DrawerButton(final Context pContext, final AttributeSet pAttrs) {
        super(pContext, pAttrs);
        init(pAttrs);
    }

    public DrawerButton(final Context pContext, final AttributeSet pAttrs, final int pDefStyle) {
        super(pContext, pAttrs, pDefStyle);
        init(pAttrs);
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_drawer_button, this);

        mButtonView = findViewById(R.id.drawer_item_button);
    }

    @Override
    public void setOnClickListener(@Nullable final OnClickListener pOnClickListener) {
        mButtonView.setOnClickListener(pOnClickListener);
    }

    private void init() {
        inflate();
    }

    private void init(final AttributeSet pAttrs) {
        inflate();

        @SuppressLint("CustomViewStyleable") final TypedArray typedArray = getContext().obtainStyledAttributes(pAttrs, R.styleable.mg_drawer_button, 0, 0);

        try {
            final String text = typedArray.getString(R.styleable.mg_drawer_button_item_title);

            setText(text);
        } finally {
            typedArray.recycle();
        }
    }

    private void setText(final CharSequence pText) {
        mButtonView.setText(pText);
    }
}