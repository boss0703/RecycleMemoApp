package com.example.recyclememoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclememoapp.R;
import com.example.recyclememoapp.model.RowData;
import com.example.recyclememoapp.viewholder.MemoListViewHolder;

import java.util.List;

public class MemoListRecycleViewAdapter extends RecyclerView.Adapter<MemoListViewHolder> {
    private List<RowData> list;

    public MemoListRecycleViewAdapter(List<RowData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MemoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new MemoListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MemoListViewHolder holder, int position){
        holder.titleView.setText(list.get(position).getTitle());
        holder.detailView.setText(list.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
