package com.example.mvmax.mindgames.gamecard.info;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RulesView;

import java.util.ArrayList;
import java.util.List;

public class GameCardRulesFragment extends Fragment {

    private List<RuleModel> mRuleModelList;
    private RulesView mRulesView;

    public static Fragment newInstance(@NonNull final List<RuleModel> pRuleModelList) {
        final GameCardRulesFragment baseFragment = new GameCardRulesFragment();

        baseFragment.mRuleModelList = pRuleModelList;

        return baseFragment;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_rules, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        updateRules();
    }

    private void initViews(final View pView) {
        mRulesView = pView.findViewById(R.id.game_fragment_rules_view);
    }

    private void updateRules() {
        mRulesView.setItems(mRuleModelList);
    }
}