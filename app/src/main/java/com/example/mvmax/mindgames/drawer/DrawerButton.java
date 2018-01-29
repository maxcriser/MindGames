package com.example.mvmax.mindgames.drawer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;

public class DrawerButton extends RelativeLayout {

    private TextView mButtonTextView;

    public DrawerButton(final Context context) {
        super(context);
        init();
    }

    public DrawerButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DrawerButton(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_drawer_button, this);

        mButtonTextView = findViewById(R.id.drawer_item_button);
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
        mButtonTextView.setText(pText);
    }
}