package com.example.mvmax.mindgames.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.util.UiUtils;

public class BaseFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return null;
    }

    public void setStatusBarPadding() {
        final View view = getView();

        if (view != null) {
            final View contentView = view.findViewById(R.id.fragment_base_content);

            contentView.setPadding(0, UiUtils.getStatusBarHeight(getContext()), 0, 0);
        }
    }
}