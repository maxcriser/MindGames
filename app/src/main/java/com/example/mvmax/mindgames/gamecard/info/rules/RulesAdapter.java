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

    private List<RuleModel> mList;

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

    public RulesAdapter(final List<RuleModel> pList) {
        mList = pList;
    }

    public void setData(final List<RuleModel> pList) {
        mList = pList;
    }

    @Override
    public RuleViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rule, parent, false);

        return new RuleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RuleViewHolder holder, final int pCurrentPosition) {
        final RuleModel item = mList.get(pCurrentPosition);

        holder.itemView.getViewTreeObserver().addOnPreDrawListener(holder);
        holder.mTitle.getViewTreeObserver().addOnPreDrawListener(holder);
        holder.mDescription.getViewTreeObserver().addOnPreDrawListener(holder);

        holder.mTitle.setText(item.getTitle());
        holder.mDescription.setText(item.getDescription());

        if (pCurrentPosition == 0) {
            holder.mTopLine.setVisibility(View.INVISIBLE);
        }

        if (pCurrentPosition == getItemCount() - 1) {
            holder.mBottomLine.setVisibility(View.INVISIBLE);
        } else {
            final int[] lineHeight = {0};
            final ViewGroup.LayoutParams layoutParams = holder.mBottomLine.getLayoutParams();

            holder.mTitle.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    lineHeight[0] += holder.mTitle.getHeight();

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        holder.mTitle.getViewTreeObserver().removeOnPreDrawListener(this);
                    } else {
                        holder.mTitle.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            holder.mDescription.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    lineHeight[0] += holder.mDescription.getHeight();

                    layoutParams.height = lineHeight[0];
                    holder.mBottomLine.setLayoutParams(layoutParams);

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        holder.mDescription.getViewTreeObserver().removeOnPreDrawListener(this);
                    } else {
                        holder.mDescription.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}