package com.example.mvmax.mindgames.gamecard.info;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.flex.ObservableRecyclerView;
import com.example.mvmax.mindgames.flex.ObservableScrollViewCallbacks;
import com.example.mvmax.mindgames.gamecard.info.example.DialogExampleAdapter;
import com.example.mvmax.mindgames.gamecard.info.example.ExampleMessageModel;
import com.example.mvmax.mindgames.gamecollection.GameCollectionFragment;

import java.util.List;

public class GameCardExampleFragment extends Fragment {

    private List<ExampleMessageModel> mExampleMessageModels;

    public static Fragment newInstance(@NonNull final List<ExampleMessageModel> pExampleMessageModels) {
        final GameCardExampleFragment gameCardExampleFragment = new GameCardExampleFragment();

        gameCardExampleFragment.mExampleMessageModels = pExampleMessageModels;

        return gameCardExampleFragment;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_game_example, container, false);

        final Activity parentActivity = getActivity();
        final ObservableRecyclerView recyclerView = view.findViewById(R.id.scroll);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
        recyclerView.setHasFixedSize(false);

        recyclerView.setAdapter(new DialogExampleAdapter(mExampleMessageModels));

        recyclerView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.container));

        if (parentActivity instanceof ObservableScrollViewCallbacks) {
            recyclerView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
        }
        return view;
    }
}