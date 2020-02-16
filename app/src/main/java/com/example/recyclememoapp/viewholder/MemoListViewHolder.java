package com.example.recyclememoapp.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclememoapp.R;

public class MemoListViewHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public TextView detailView;
    public MemoListViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.title);
        detailView = itemView.findViewById(R.id.detail);

    }
}
