package com.example.mvmax.mindgames.games.guessthestory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.games.guessthestory.holder.GuessTheStoryHolder;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameItemModel;
import com.github.florent37.expansionpanel.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GuessTheStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private List<GuessTheStoryGameItemModel> mList = new ArrayList<>();
    private final View mHeaderView;
    private final ExpansionLayoutCollection mExpansionsCollection = new ExpansionLayoutCollection();

    public GuessTheStoryAdapter(final List<GuessTheStoryGameItemModel> pList, final View headerView) {
        mExpansionsCollection.openOnlyOne(true);
        mList = pList;
        mHeaderView = headerView;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null) {
            return mList.size();
        } else {
            return mList.size() + 1;
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return (position == 0 && mHeaderView != null) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new HeaderViewHolder(mHeaderView);
        } else {
            return GuessTheStoryHolder.buildFor(parent);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof GuessTheStoryHolder) {
            final GuessTheStoryHolder holder = (GuessTheStoryHolder) viewHolder;

            holder.bind(mList.get(position));

            mExpansionsCollection.add(holder.getExpansionLayout());
        }
    }

    void setItems(final Collection<GuessTheStoryGameItemModel> items) {
        this.mList.addAll(items);
        notifyDataSetChanged();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        HeaderViewHolder(final View view) {
            super(view);
        }
    }
}