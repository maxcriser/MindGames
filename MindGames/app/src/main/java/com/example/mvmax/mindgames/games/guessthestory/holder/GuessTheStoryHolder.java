package com.example.mvmax.mindgames.games.guessthestory.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameItemModel;
import com.github.florent37.expansionpanel.ExpansionLayout;

public final class GuessTheStoryHolder extends RecyclerView.ViewHolder {

    private final ExpansionLayout expansionLayout;
    private final TextView mTask;
    private final TextView mAnswer;
    private final TextView mName;

    public static GuessTheStoryHolder buildFor(final ViewGroup viewGroup) {
        return new GuessTheStoryHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guess_the_story_recycler_cell, viewGroup, false));
    }

    private GuessTheStoryHolder(final View itemView) {
        super(itemView);
        expansionLayout = itemView.findViewById(R.id.expansionLayout);
        mTask = itemView.findViewById(R.id.guess_the_story_list_item_task);
        mName = itemView.findViewById(R.id.guess_the_story_list_item_name);
        mAnswer = itemView.findViewById(R.id.guess_the_story_list_item_answer);
    }

    public void bind(final GuessTheStoryGameItemModel pItem) {
        expansionLayout.collapse(false);
        mTask.setText(pItem.getTask());
        mAnswer.setText(pItem.getAnswer());
        mName.setText(pItem.getName());
    }

    public ExpansionLayout getExpansionLayout() {
        return expansionLayout;
    }
}