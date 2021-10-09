package com.example.nutritionapp;

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

        mImageView = (ImageView) findViewById(R.id.profile_picture);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent,1);
            }
        });


        setPrequistes();
        setPrequistesXml();
        dm=getIntent().getExtras().getParcelable("obj");
        updateUI();
    }

    private void updateUI() {
        Glide.with(this)
                .load(dm.getImageUrl())
                .error(R.drawable.ic_launcher_background)
                .override(100,100)
                .into(google_imgw);
        nametxt.setText("Displayed Name: \n"+dm.getName());
        emailtxt.setText("Email: \n"+dm.getEmail());
        nametxt2.setText("Given Name \n"+dm.getName2());


    }

    private void setPrequistesXml() {
        nametxt=findViewById(R.id.nametxt);
        emailtxt=findViewById(R.id.emailtxt);
        nametxt2=findViewById(R.id.nametxt2);
        google_imgw=findViewById(R.id.google_imgw);
    }

    private void setPrequistes() {
        dm=new DataModal();
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
