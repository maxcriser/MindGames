package com.example.mvmax.mindgames.gamecard.info.example;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class DialogExampleAdapter extends RecyclerView.Adapter<DialogExampleAdapter.DialogExampleViewHolder> {

    @IntDef({Type.INFO, Type.PRESENTER, Type.PLAYER, Type.FINISH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {

        int INFO = 0;
        int PRESENTER = 1;
        int PLAYER = 2;
        int FINISH = 3;

    }

    private List<ExampleMessageModel> mMessageModels;

    DialogExampleAdapter(final List<ExampleMessageModel> pMessageModels) {
        mMessageModels = pMessageModels;
    }

    @Override
    public DialogExampleViewHolder onCreateViewHolder(final ViewGroup pParent, final int pViewType) {
        final int layout;

        switch (pViewType) {
            case Type.PLAYER:
                layout = R.layout.adapter_dialog_message_player;

                break;
            case Type.PRESENTER:
                layout = R.layout.adapter_dialog_message_presenter;

                break;
            case Type.FINISH:
                layout = R.layout.adapter_dialog_message_finish;

                break;
            default:
                layout = R.layout.adapter_dialog_message_info;

                break;
        }

        final View itemView = LayoutInflater.from(pParent.getContext()).inflate(layout, pParent, false);

        return new DialogExampleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DialogExampleViewHolder pHolder, final int pPosition) {
        final ExampleMessageModel item = mMessageModels.get(pPosition);

        pHolder.mMessageView.setText(item.getMessage());
    }

    @Override
    public int getItemViewType(final int pPosition) {
        switch (mMessageModels.get(pPosition).getType()) {
            case ExampleMessageModel.DialogMessageType.INFO:
                return Type.INFO;
            case ExampleMessageModel.DialogMessageType.PLAYER:
                return Type.PLAYER;
            case ExampleMessageModel.DialogMessageType.PRESENTER:
                return Type.PRESENTER;
            default:
                return Type.FINISH;
        }
    }

    @Override
    public int getItemCount() {
        return mMessageModels.size();
    }

    final class DialogExampleViewHolder extends RecyclerView.ViewHolder {

        private final TextView mMessageView;

        private DialogExampleViewHolder(final View pView) {
            super(pView);

            mMessageView = pView.findViewById(R.id.dialog_example_message);
        }
    }
}