package com.example.nutritionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class blogAdapter extends RecyclerView.Adapter<blogAdapter.ViewHolder> {
    @NonNull
    @Override
    public blogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.blog_prototype_button, parent, false);
        ViewHolder viewHolder=  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull blogAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button blogLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blogLink= itemView.findViewById(R.id.blog_link_b);
        }
    }
}
