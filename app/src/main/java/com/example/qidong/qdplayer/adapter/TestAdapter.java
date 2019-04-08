package com.example.qidong.qdplayer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author qidong
 * @date 2019/4/8
 * desc
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder>{

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    class TestHolder extends RecyclerView.ViewHolder {
        public TestHolder(View itemView) {
            super(itemView);
        }
    }
}
