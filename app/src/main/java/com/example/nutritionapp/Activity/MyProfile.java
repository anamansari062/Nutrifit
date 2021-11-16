package com.example.nutritionapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.nutritionapp.R;

import java.io.IOException;

public class MyProfile extends AppCompatActivity {
//get data from google

    DataModal dm;
    ImageView google_imgw;
    TextView nametxt,emailtxt,nametxt2;
    private Uri imagePath;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

    }

}
