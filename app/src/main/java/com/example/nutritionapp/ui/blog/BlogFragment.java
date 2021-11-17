package com.example.nutritionapp.ui.blog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritionapp.R;
import com.example.nutritionapp.adapters.BlogAdapter;
import com.example.nutritionapp.databinding.FragmentBlogBinding;

import java.util.ArrayList;


public class BlogFragment extends Fragment {

    private FragmentBlogBinding binding;
    RecyclerView blog_recycler;
    BlogAdapter blog_adapter;
    TextView blogLink;
    ArrayList<String> blogLinks = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BlogViewModel blogViewModel = new ViewModelProvider(this).get(BlogViewModel.class);

        binding = FragmentBlogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        blog_recycler = root.findViewById(R.id.blogRecycler);
        blogLink= root.findViewById(R.id.blog_link);
        blog_adapter = new BlogAdapter();
        blog_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        blog_recycler.setAdapter(blog_adapter);

        final TextView textView = binding.textBlog;
        blogViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}