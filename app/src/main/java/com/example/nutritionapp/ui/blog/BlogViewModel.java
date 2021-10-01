package com.example.nutritionapp.ui.blog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritionapp.R;
import com.example.nutritionapp.blogAdapter;

public class BlogViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public BlogViewModel() {
         mText = new MutableLiveData<>();
         mText.setValue("Blogs Available");

    }

    public LiveData<String> getText() {
        return mText;
    }
}