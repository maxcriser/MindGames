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

    public RulesView(final Context context) {
        super(context);
        init();
    }

    public RulesView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RulesView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
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

    public void setItems(final List<RuleModel> pRuleModelList) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        final RulesAdapter mRulesAdapter = new RulesAdapter(pRuleModelList);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRulesAdapter);
    }
}