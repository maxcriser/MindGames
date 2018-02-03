package com.example.mvmax.mindgames.fragment;

import android.content.Context;
import android.content.Intent;
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
    public View onCreateView(@NonNull final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        return null;
    }

    public void setStatusBarPadding() {
        final View view = getView();

        if (view == null) {
            return;
        }

        final View contentView = view.findViewById(R.id.fragment_base_content);
        contentView.setPadding(0, UiUtils.getStatusBarHeight(getContext()), 0, 0);
    }

    public void openGameActivity(final Context pContext, @NonNull final Class pActivityClass) {
        startActivity(new Intent(pContext, pActivityClass));
    }
}