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

    public ExampleView(final Context pContext) {
        super(pContext);
        init();
    }

    public ExampleView(final Context pContext, final AttributeSet pAttrs) {
        super(pContext, pAttrs);
        init(pAttrs);
    }

    public ExampleView(final Context pContext, final AttributeSet pAttrs, final int pDefStyle) {
        super(pContext, pAttrs, pDefStyle);
        init(pAttrs);
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

    public void setItems(final List<ExampleMessageModel> pMessageModels) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        final DialogExampleAdapter mDialogDialogExampleAdapter = new DialogExampleAdapter(pMessageModels);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mDialogDialogExampleAdapter);
    }
}