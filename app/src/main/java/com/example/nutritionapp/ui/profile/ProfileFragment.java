package com.example.nutritionapp.ui.profile;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProfileFragment extends Fragment {
    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    TextView nametxt,emailtxt,gender,height,age,weight,mobile,activity,calories;
    DatabaseReference databaseReference;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        nametxt= root.findViewById(R.id.nametxt);
        emailtxt= root.findViewById(R.id.emailtxt);
        gender= root.findViewById(R.id.gendertxt);
        height= root.findViewById(R.id.heighttxt);
        age= root.findViewById(R.id.agetxt);
        mobile= root.findViewById(R.id.mobiletxt);
        weight= root.findViewById(R.id.weighttxt);
        activity= root.findViewById(R.id.act);
        calories=root.findViewById(R.id.calorie);
        progressBar= root.findViewById(R.id.progress_bar);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("USERS");
        if (user != null )
        {
            timer();
            databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Map<String, String> map= (Map<String, String>) snapshot.getValue();
                    String name= map.get("name");
                    String email= map.get("email");
                    String Gender= map.get("gender");
                    String Height= map.get("height");
                    String Weight= map.get("weight");
                    String Age=map.get("age");
                    String Mobile= map.get("mobile");
                    String Act= map.get("activity");
                    String Calories= map.get("calories");
                    nametxt.setText(name);
                    emailtxt.setText(email);
                    gender.setText(Gender);
                    height.setText(String.format("%s cm", Height));
                    age.setText(String.format("%s yrs", Age));
                    mobile.setText(Mobile);
                    weight.setText(String.format("%sKg", Weight));
                    activity.setText(Act);
                    float totalCaloriesEaten = 0;
                    if (Calories != null) {
                        totalCaloriesEaten = Float.parseFloat(Calories);
                    }
                    calories.setText( String.valueOf(round(totalCaloriesEaten, 2)));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void timer() {
        new CountDownTimer(1500, 1000) {

            public void onTick(long millisUntilFinished) {
                progressBar.setVisibility(View.VISIBLE);
            }

            public void onFinish() {
                progressBar.setVisibility(View.GONE);
            }

        }.start();
    }
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}