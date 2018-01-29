package com.example.mvmax.mindgames.gamecard.info.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;

import java.util.Collection;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.DialogExampleViewHolder> {

    private List<ExampleMessageModel> mList;

    final class DialogExampleViewHolder extends RecyclerView.ViewHolder {

        private final TextView mMessage;

        private DialogExampleViewHolder(final View view) {
            super(view);

            mMessage = view.findViewById(R.id.dialog_example_message);
        }
    }

    ExampleAdapter(final List<ExampleMessageModel> pList) {
        mList = pList;
    }

    public void setData(final List<ExampleMessageModel> pList) {
        mList = pList;
    }

    @Override
    public DialogExampleViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final int layout;

        switch (viewType) {
            case ExampleMessageModel.DialogMessageType.PLAYER:
                layout = R.layout.adapter_dialog_message_player;

                break;
            case ExampleMessageModel.DialogMessageType.PRESENTER:
                layout = R.layout.adapter_dialog_message_presenter;

                break;
            case ExampleMessageModel.DialogMessageType.FINISH:
                layout = R.layout.adapter_dialog_message_finish;

                break;
            default:
                layout = R.layout.adapter_dialog_message_info;

                break;
        }

        final View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new DialogExampleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DialogExampleViewHolder holder, final int pCurrentPosition) {
        final ExampleMessageModel item = mList.get(pCurrentPosition);

        holder.mMessage.setText(item.getMessage());
    }

    @Override
    public int getItemViewType(final int pPosition) {
        return mList.get(pPosition).getType();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}