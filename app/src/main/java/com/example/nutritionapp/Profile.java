package com.example.nutritionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class Profile extends AppCompatActivity {
//get data from google

    DataModal dm;
    ImageView google_imgw;
    TextView nametxt,emailtxt,nametxt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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

}
