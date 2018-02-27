package com.example.mvmax.mindgames.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.util.UiUtil;

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
        contentView.setPadding(0, UiUtil.getStatusBarHeight(getContext()), 0, 0);
    }

    public void setBackgroundDrawable(final int pPoster) {
        final Activity activity = getActivity();

        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).setBackgroundDrawable(pPoster);
        }
    }

    public void setBackgroundUrl(final String pPoster) {
        final Activity activity = getActivity();

        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).setBackgroundUrl(pPoster);
        }
    }

    public void enableDrawer() {
        final Activity activity = getActivity();

        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).enableDrawer();
        }
    }

    public void disableDrawer() {
        final Activity activity = getActivity();

        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).disableDrawer();
        }
    }

    @Nullable
    public Toolbar findToolbarView() {
        final View view = getView();

        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.toolbar_view);
    }
}