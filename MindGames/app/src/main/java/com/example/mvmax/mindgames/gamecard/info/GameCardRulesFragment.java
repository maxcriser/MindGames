package com.example.mvmax.mindgames.gamecard.info;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.flex.ObservableRecyclerView;
import com.example.mvmax.mindgames.flex.ObservableScrollViewCallbacks;
import com.example.mvmax.mindgames.gamecard.info.rules.RuleModel;
import com.example.mvmax.mindgames.gamecard.info.rules.RulesAdapter;
import com.example.mvmax.mindgames.timeline.TimelineView;

import java.util.List;

public class GameCardRulesFragment extends Fragment {

    private List<RuleModel> mRuleModelList;

    public static Fragment newInstance(@NonNull final List<RuleModel> pRuleModelList) {
        final GameCardRulesFragment baseFragment = new GameCardRulesFragment();

        baseFragment.mRuleModelList = pRuleModelList;

        return baseFragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_game_rules, container, false);

        final Activity parentActivity = getActivity();
        final ObservableRecyclerView recyclerView = view.findViewById(R.id.scroll);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
        recyclerView.setHasFixedSize(false);

        TimelineView timelineView = view.findViewById(R.id.time_marker);
        timelineView.initLine(0);
        timelineView.initLine(0);
        timelineView.initLine(0);
        timelineView.initLine(0);
        timelineView.initLine(0);
        timelineView.initLine(0);

        recyclerView.setAdapter(new RulesAdapter(mRuleModelList));

        recyclerView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.container));

        if (parentActivity instanceof ObservableScrollViewCallbacks) {
            recyclerView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
        }
        return view;
    }

    public static class TimeLineViewHolder extends RecyclerView.ViewHolder {

        public TimelineView mTimelineView;
        public final TextView mTitle;
        public final TextView mDescription;

        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.rule_title);
            mDescription = itemView.findViewById(R.id.rule_description);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);
        }
    }
}