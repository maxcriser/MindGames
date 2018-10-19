package com.example.mvmax.mindgames.games.guessthestory.holder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.clicklistener.BuyPremiumClickListener;
import com.example.mvmax.mindgames.database.DatabaseHolder;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameCompletedItemModel;
import com.example.mvmax.mindgames.games.guessthestory.model.GuessTheStoryGameItemModel;
import com.example.mvmax.mindgames.util.AppUtils;
import com.example.mvmax.mindgames.util.UiUtil;
import com.github.florent37.expansionpanel.ExpansionLayout;

public final class GuessTheStoryHolder extends RecyclerView.ViewHolder {

    private final View mLockIcon;
    private final ExpansionLayout mExpansionLayout;
    private final View mBuyPremiumButton;
    private final View mContent;
    private final View mRestrictedView;
    private final TextView mTask;
    private final TextView mAnswer;
    private final TextView mName;
    private final CheckBox mMarkAsDone;

    public static GuessTheStoryHolder buildFor(final ViewGroup viewGroup) {
        return new GuessTheStoryHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guess_the_story_recycler_cell, viewGroup, false));
    }

    private GuessTheStoryHolder(final View itemView) {
        super(itemView);
        mExpansionLayout = itemView.findViewById(R.id.expansionLayout);
        mContent = itemView.findViewById(R.id.cell_content);
        mBuyPremiumButton = itemView.findViewById(R.id.buy_premium_account_button);
        mRestrictedView = itemView.findViewById(R.id.container_buy_premium);
        mTask = itemView.findViewById(R.id.guess_the_story_list_item_task);
        mName = itemView.findViewById(R.id.guess_the_story_list_item_name);
        mAnswer = itemView.findViewById(R.id.guess_the_story_list_item_answer);
        mMarkAsDone = itemView.findViewById(R.id.guess_the_story_mark_as_done_check_box);
        mLockIcon = itemView.findViewById(R.id.lock_indicator);
    }

    public void bind(final GuessTheStoryGameItemModel pItem) {
        final boolean isCompleted = pItem.isCompleted();

        mExpansionLayout.collapse(false);
        mTask.setText(pItem.getTask());
        mAnswer.setText(pItem.getAnswer());
        mName.setText(pItem.getName());
        mMarkAsDone.setChecked(isCompleted);

        UiUtil.setStrikeThruTextView(isCompleted, mName);

        mMarkAsDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(final CompoundButton pCompoundButton, final boolean pIsChecked) {
                final String uniqueId = pItem.getUniqueId();

                UiUtil.setStrikeThruTextView(pIsChecked, mName);

                if (pIsChecked) {
                    addToDatabase(uniqueId);
                } else {
                    removeFromDatabase(uniqueId);
                }
            }
        });

        if (AppUtils.isContentAvailable(itemView.getContext(), pItem.isPaid())) {
            mLockIcon.setVisibility(View.GONE);
            mContent.setVisibility(View.VISIBLE);
            mRestrictedView.setVisibility(View.GONE);
        } else {
            mContent.setVisibility(View.GONE);
            mLockIcon.setVisibility(View.VISIBLE);
            mRestrictedView.setVisibility(View.VISIBLE);
            mBuyPremiumButton.setOnClickListener(new BuyPremiumClickListener());
        }
    }

    // TODO: 14.05.2018 move to executable
    private void addToDatabase(final String pUniqueId) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        final ContentValues newItem = new ContentValues();

        newItem.put(GuessTheStoryGameCompletedItemModel.Field.ID, (Integer) null);
        newItem.put(GuessTheStoryGameCompletedItemModel.Field.UNIQUE_ID, pUniqueId);

        mDatabase.insert(GuessTheStoryGameCompletedItemModel.Field.TABLE, null, newItem);
        mDatabase.close();
    }

    // TODO: 14.05.2018 move to executable
    // TODO: 14.05.2018 move " = ?" to databaseconstants
    private void removeFromDatabase(final String pUniqueId) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        mDatabase.delete(GuessTheStoryGameCompletedItemModel.Field.TABLE,
                GuessTheStoryGameCompletedItemModel.Field.UNIQUE_ID + " = ?",
                new String[]{pUniqueId});
        mDatabase.close();
    }

    public ExpansionLayout getExpansionLayout() {
        return mExpansionLayout;
    }
}