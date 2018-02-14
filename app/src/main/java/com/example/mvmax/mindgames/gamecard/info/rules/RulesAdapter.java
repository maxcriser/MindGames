package com.example.mvmax.mindgames.gamecard.info.rules;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;

import java.util.List;

public class RulesAdapter extends RecyclerView.Adapter<RulesAdapter.RuleViewHolder> {

    private final List<RuleModel> mRuleModels;

    public RulesAdapter(final List<RuleModel> pRuleModels) {
        mRuleModels = pRuleModels;
    }

    @Override
    public RuleViewHolder onCreateViewHolder(final ViewGroup pParent, final int pViewType) {
        final View itemView = LayoutInflater.from(pParent.getContext()).inflate(R.layout.adapter_rule, pParent, false);

        return new RuleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RuleViewHolder pHolder, final int pCurrentPosition) {
        final RuleModel item = mRuleModels.get(pCurrentPosition);

        pHolder.itemView.getViewTreeObserver().addOnPreDrawListener(pHolder);
        pHolder.mTitle.getViewTreeObserver().addOnPreDrawListener(pHolder);
        pHolder.mDescription.getViewTreeObserver().addOnPreDrawListener(pHolder);

        pHolder.mTitle.setText(item.getTitle());
        pHolder.mDescription.setText(item.getDescription());

        if (pCurrentPosition == 0) {
            pHolder.mTopLine.setVisibility(View.INVISIBLE);
        }

        if (pCurrentPosition == getItemCount() - 1) {
            pHolder.mBottomLine.setVisibility(View.INVISIBLE);
        } else {
            final int[] lineHeight = {0};
            final ViewGroup.LayoutParams layoutParams = pHolder.mBottomLine.getLayoutParams();

            pHolder.mTitle.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    lineHeight[0] += pHolder.mTitle.getHeight();

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        pHolder.mTitle.getViewTreeObserver().removeOnPreDrawListener(this);
                    } else {
                        pHolder.mTitle.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            pHolder.mDescription.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    lineHeight[0] += pHolder.mDescription.getHeight();

                    layoutParams.height = lineHeight[0];
                    pHolder.mBottomLine.setLayoutParams(layoutParams);

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        pHolder.mDescription.getViewTreeObserver().removeOnPreDrawListener(this);
                    } else {
                        pHolder.mDescription.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mRuleModels.size();
    }

    final class RuleViewHolder extends RecyclerView.ViewHolder implements ViewTreeObserver.OnPreDrawListener {

        private final TextView mTitle;
        private final TextView mDescription;
        private final FrameLayout mTopLine;
        private final FrameLayout mBottomLine;

        private RuleViewHolder(final View view) {
            super(view);

            mTitle = view.findViewById(R.id.rule_title);
            mDescription = view.findViewById(R.id.rule_description);
            mTopLine = view.findViewById(R.id.rule_top_line);
            mBottomLine = view.findViewById(R.id.rule_bottom_line);
        }

        @Override
        public boolean onPreDraw() {
            itemView.getViewTreeObserver().removeOnPreDrawListener(this);
            mTitle.getViewTreeObserver().removeOnPreDrawListener(this);
            mDescription.getViewTreeObserver().removeOnPreDrawListener(this);

            return true;
        }
    }
}