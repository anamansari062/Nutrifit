package com.example.nutritionapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyProfile extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private Uri imagePath;
    private ImageView mImageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        mImageView = (ImageView) findViewById(R.id.profile_picture);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent photoIntent = new Intent(Intent.ACTION_PICK);
                    photoIntent.setType("image/*");
                    startActivityForResult(photoIntent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data!=null){
            imagePath = data.getData();
            getImageInImageView();
        }
    }

    private void getImageInImageView() {

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mImageView.setImageBitmap(bitmap);

    }
}
