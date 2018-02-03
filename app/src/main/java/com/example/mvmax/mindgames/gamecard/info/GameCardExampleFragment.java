package com.example.mvmax.mindgames.gamecard.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleView;

import java.util.List;

public class GameCardExampleFragment extends Fragment {

    private List<ExampleMessageModel> mExampleMessageModels;
    private ExampleView mExampleView;

    public static Fragment newInstance(@NonNull final List<ExampleMessageModel> pExampleMessageModels) {
        final GameCardExampleFragment gameCardExampleFragment = new GameCardExampleFragment();

        gameCardExampleFragment.mExampleMessageModels = pExampleMessageModels;

        return gameCardExampleFragment;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        return pInflater.inflate(R.layout.fragment_game_example, pContainer, false);
    }

    @Override
    public void onViewCreated(@NonNull final View pView, @Nullable final Bundle pSavedInstanceState) {
        super.onViewCreated(pView, pSavedInstanceState);

        initViews(pView);
        updateDialog();
    }

    private void initViews(final View pView) {
        mExampleView = pView.findViewById(R.id.game_fragment_dialog_example_view);
    }

    private void updateDialog() {
        mExampleView.setItems(mExampleMessageModels);
    }
}