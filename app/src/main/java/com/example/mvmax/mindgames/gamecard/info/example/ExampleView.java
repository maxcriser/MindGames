package com.example.mvmax.mindgames.gamecard.info.example;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.mvmax.mindgames.R;

import java.util.List;

public class ExampleView extends RelativeLayout {

    private RecyclerView mRecyclerView;

    public ExampleView(final Context context) {
        super(context);
        init();
    }

    public ExampleView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ExampleView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_dialog_example, this);

        mRecyclerView = findViewById(R.id.dialog_example_view_recycler_view);
    }

    private void init() {
        inflate();
    }

    private void init(final AttributeSet pAttrs) {
        inflate();
    }

    public void setItems(final List<ExampleMessageModel> pDialogExampleModelList) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        final ExampleAdapter mDialogExampleAdapter = new ExampleAdapter(pDialogExampleModelList);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mDialogExampleAdapter);
    }
}