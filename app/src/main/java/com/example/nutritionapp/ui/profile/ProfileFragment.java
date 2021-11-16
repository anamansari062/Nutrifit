package com.example.nutritionapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView nametxt,emailtxt,gender,height,age,weight,mobile;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        nametxt=(TextView) root.findViewById(R.id.nametxt);
        emailtxt=(TextView) root.findViewById(R.id.emailtxt);
        gender=(TextView) root.findViewById(R.id.gendertxt);
        height=(TextView) root.findViewById(R.id.heighttxt);
        age=(TextView) root.findViewById(R.id.agetxt);
        mobile=(TextView) root.findViewById(R.id.mobiletxt);
        weight=(TextView) root.findViewById(R.id.weighttxt);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("USERS");
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
                nametxt.setText(name);
                emailtxt.setText(email);
                gender.setText(Gender);
                height.setText(Height);
                age.setText(Age);
                mobile.setText(Mobile);
                weight.setText(Weight);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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