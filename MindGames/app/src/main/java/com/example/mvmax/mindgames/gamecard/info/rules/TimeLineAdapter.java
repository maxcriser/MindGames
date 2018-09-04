package com.example.mvmax.mindgames.gamecard.info.rules;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.gamecard.info.GameCardRulesFragment;

import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<GameCardRulesFragment.TimeLineViewHolder> {

    private final List<RuleModel> mRuleModels;

    public TimeLineAdapter(final List<RuleModel> pRuleModels) {
        mRuleModels = pRuleModels;
    }

    @Override
    public GameCardRulesFragment.TimeLineViewHolder onCreateViewHolder(final ViewGroup pParent, final int pViewType) {
        final View itemView = LayoutInflater.from(pParent.getContext()).inflate(R.layout.adapter_rule, pParent, false);

        return new GameCardRulesFragment.TimeLineViewHolder(itemView, 0);
    }

    @Override
    public void onBindViewHolder(final GameCardRulesFragment.TimeLineViewHolder pHolder, final int pCurrentPosition) {
        final RuleModel item = mRuleModels.get(pCurrentPosition);

        pHolder.mTitle.setText(item.getTitle());
        pHolder.mDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mRuleModels.size();
    }
}