package com.example.nutritionapp.adapters;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritionapp.R;

import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
    ArrayList<Integer> blogLink = new ArrayList<>();
    @Override
    public BlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.blog_prototype, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.ViewHolder holder, int position) {
        blogLink.add(R.string.Blog1);
        blogLink.add(R.string.Blog2);
        blogLink.add(R.string.Blog3);
        blogLink.add((R.string.Blog4));
        blogLink.add((R.string.Blog5));
        blogLink.add((R.string.Blog6));
        blogLink.add((R.string.Blog7));
        blogLink.add((R.string.Blog8));
        blogLink.add((R.string.Blog9));
        blogLink.add((R.string.Blog10));
        holder.blogLink.setText(blogLink.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView blogLinkLayout;
        TextView blogLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blogLinkLayout= itemView.findViewById(R.id.blog_link_layout);
            blogLink= itemView.findViewById(R.id.blog_link);
        }
    }
}
