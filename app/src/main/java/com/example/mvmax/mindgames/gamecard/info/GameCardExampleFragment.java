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
import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleView;

import java.util.ArrayList;
import java.util.List;

public class GameCardExampleFragment extends Fragment {

    List<ExampleMessageModel> mExampleMessageModelList;
    ExampleView mExampleView;

    public static Fragment newInstance(@NonNull final List<ExampleMessageModel> pExampleMessageModelList) {
        final GameCardExampleFragment gameCardExampleFragment = new GameCardExampleFragment();

        gameCardExampleFragment.mExampleMessageModelList = pExampleMessageModelList;

        return gameCardExampleFragment;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_example, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        updateDialog();
    }

    private void initViews(final View pView) {
        mExampleView = pView.findViewById(R.id.game_fragment_dialog_example_view);
    }

    private void updateDialog() {
        mExampleView.setItems(mExampleMessageModelList);
    }
}