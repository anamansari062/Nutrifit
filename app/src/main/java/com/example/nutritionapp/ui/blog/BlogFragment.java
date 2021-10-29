package com.example.nutritionapp.ui.blog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritionapp.R;
import com.example.nutritionapp.adapters.blogAdapter;
import com.example.nutritionapp.databinding.FragmentBlogBinding;


public class BlogFragment extends Fragment {

    private BlogViewModel blogViewModel;
    private FragmentBlogBinding binding;
    RecyclerView blog_recycler;
    blogAdapter blog_adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        blogViewModel = new ViewModelProvider(this).get(BlogViewModel.class);

        binding = FragmentBlogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        blog_recycler = root.findViewById(R.id.blogRecycler);
        blog_adapter = new blogAdapter();
        blog_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        blog_recycler.setAdapter(blog_adapter);

        final TextView textView = binding.textBlog;
        blogViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
          return root;
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}