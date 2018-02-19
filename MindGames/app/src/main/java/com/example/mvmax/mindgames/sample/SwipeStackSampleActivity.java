package com.example.mvmax.mindgames.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mvmax.mindgames.R;
import com.example.mvmax.mindgames.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class SwipeStackSampleActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_sample_swipe_stack);

        final List<String> words = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            words.add("item === " + i);
        }

//        https://www.uplabs.com/posts/swipestack
        final SwipeStack swipeStack = findViewById(R.id.swipeStack);
        swipeStack.setAdapter(new SwipeStackAdapter(words));
    }

    public class SwipeStackAdapter extends BaseAdapter {

        private final List<String> mData;

        public SwipeStackAdapter(final List<String> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(final int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            @SuppressLint("ViewHolder") final View convertView1 = getLayoutInflater().inflate(R.layout.adapter_sample_swipe_stack_item, parent, false);
            final TextView textViewCard = convertView1.findViewById(R.id.text_view_card);
            textViewCard.setText(mData.get(position));

            return convertView1;
        }
    }
}
