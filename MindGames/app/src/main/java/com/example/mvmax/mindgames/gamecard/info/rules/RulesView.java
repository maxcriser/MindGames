package com.example.mvmax.mindgames.gamecard.info.rules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.mvmax.mindgames.R;

import java.util.List;

public class RulesView extends RelativeLayout {

    private RecyclerView mRecyclerView;

    public RulesView(final Context pContext) {
        super(pContext);
        init();
    }

    public RulesView(final Context pContext, final AttributeSet pAttrs) {
        super(pContext, pAttrs);
        init(pAttrs);
    }

    public RulesView(final Context pContext, final AttributeSet pAttrs, final int pDefStyle) {
        super(pContext, pAttrs, pDefStyle);
        init(pAttrs);
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_rules, this);

        mRecyclerView = findViewById(R.id.rules_view_recycler_view);
    }

    private void init() {
        inflate();
    }

    private void init(final AttributeSet pAttrs) {
        inflate();
    }

    public void setItems(final List<RuleModel> pRuleModels) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        final RulesAdapter mRulesAdapter = new RulesAdapter(pRuleModels);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRulesAdapter);
    }
}