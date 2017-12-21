package com.example.mvmax.mindgames.game;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.listener.OnBackClickListener;
import com.example.mvmax.mindgames.model.GameCardModel;
import com.example.mvmax.mindgames.toolbar.Toolbar;
import com.example.mvmax.mindgames.util.UiUtils;

public class GameFragment extends BaseFragment {

    public static final String EXTRA_GAME_CARD_MODEL = "dsada";
    private AppCompatImageView mBlurredBackground;
    private AppCompatImageView mPoster;
    private TextView mName;
    private TextView mDescription;
    private GameCardModel mGameCardModel;

    public static Fragment newInstance(@NonNull final GameCardModel pGameCardModel) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_GAME_CARD_MODEL, pGameCardModel);

        final GameFragment baseFragment = new GameFragment();
        baseFragment.setArguments(bundle);

        return baseFragment;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarPadding();
        initViews();
        bindHeader();
    }

    private void bindHeader() {
        final int poster = mGameCardModel.getPoster();

        mBlurredBackground.setImageResource(poster);
        mPoster.setImageResource(poster);
        UiUtils.setTextOrHide(mName, mGameCardModel.getName());
        UiUtils.setTextOrHide(mDescription, mGameCardModel.getDescription());
    }

    private void initViews() {
        final Bundle bundle = getArguments();
        mGameCardModel = (GameCardModel) bundle.getSerializable(EXTRA_GAME_CARD_MODEL);

        final View view = getView();

        if (view != null) {
            final Toolbar toolbar = view.findViewById(R.id.toolbar_view);
            toolbar.getbackIconView().setOnClickListener(new OnBackClickListener(getContext()));

            mBlurredBackground = view.findViewById(R.id.game_fragment_background_image);
            mPoster = view.findViewById(R.id.game_fragment_poster);
            mName = view.findViewById(R.id.game_fragment_header_name);
            mDescription = view.findViewById(R.id.game_fragment_header_description);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }
}