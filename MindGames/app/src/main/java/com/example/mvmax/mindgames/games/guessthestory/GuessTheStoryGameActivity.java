package com.example.mvmax.mindgames.games.guessthestory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.games.guessthestory.executable.GuessTheStoryGameExecutable;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameItemModel;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameModel;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GuessTheStoryGameActivity extends BaseActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_story_game);

        final GuessTheStoryGameModel guessTheStoryGameModel = new GuessTheStoryGameExecutable().execute();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setItems(guessTheStoryGameModel.getList());
    }

    public static final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

        private final List<GuessTheStoryGameItemModel> mList = new ArrayList<>();
        private final ExpansionLayoutCollection mExpansionsCollection = new ExpansionLayoutCollection();

        RecyclerAdapter() {
            mExpansionsCollection.openOnlyOne(true);
        }

        @Override
        public RecyclerHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            return RecyclerHolder.buildFor(parent);
        }

        @Override
        public void onBindViewHolder(final RecyclerHolder holder, final int position) {
            holder.bind(mList.get(position));

            mExpansionsCollection.add(holder.getExpansionLayout());
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        void setItems(final Collection<GuessTheStoryGameItemModel> items) {
            this.mList.addAll(items);
            notifyDataSetChanged();
        }

        static final class RecyclerHolder extends RecyclerView.ViewHolder {

            private final ExpansionLayout expansionLayout;
            private final TextView mTask;
            private final TextView mAnswer;
            private final TextView mName;

            static RecyclerHolder buildFor(final ViewGroup viewGroup) {
                return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guess_the_story_recycler_cell, viewGroup, false));
            }

            RecyclerHolder(final View itemView) {
                super(itemView);
                expansionLayout = itemView.findViewById(R.id.expansionLayout);
                mTask = itemView.findViewById(R.id.guess_the_story_list_item_task);
                mName = itemView.findViewById(R.id.guess_the_story_list_item_name);
                mAnswer = itemView.findViewById(R.id.guess_the_story_list_item_answer);
            }

            void bind(final GuessTheStoryGameItemModel pItem) {
                expansionLayout.collapse(false);
                mTask.setText(pItem.getTask());
                mAnswer.setText(pItem.getAnswer());
                mName.setText(pItem.getName());
            }

            ExpansionLayout getExpansionLayout() {
                return expansionLayout;
            }
        }
    }
}
