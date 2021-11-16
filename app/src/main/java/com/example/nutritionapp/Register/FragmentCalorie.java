package com.example.nutritionapp.Register;




import static android.content.Context.MODE_APPEND;
import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.Activity.Login;
import com.example.nutritionapp.Activity.MainActivity;
import com.example.nutritionapp.R;
import com.example.nutritionapp.Register1;
import com.example.nutritionapp.databinding.FragmentCalorieBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;


public class FragmentCalorie extends Fragment {
    TextView calories, textEmail, textMobile, textName, textPass;
    FragmentCalorieBinding binding;
    Button register;
    //String height, weight, age, gender, active, name, pass, email, mobile, calorie;
    FirebaseAuth mAuth;
    Double a, mcalorie;


//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPass(String pass) {
//        this.pass = pass;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public void setHeight(String height) {
//        this.height = height;
//    }
//
//    public void setWeight(String weight) {
//        this.weight = weight;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public void setActive(String active) {
//        this.active = active;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getPass() {
//        return pass;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public String getHeight() {
//        return height;
//    }
//
//    public String getWeight() {
//        return weight;
//    }
//
//    public String getAge() {
//        return age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getActive() {
//        return active;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCalorieBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        calories= rootView.findViewById(R.id.display_calories);
        register= rootView.findViewById(R.id.button_register);

        textEmail= rootView.findViewById(R.id.display_email);
        textMobile= rootView.findViewById(R.id.display_mobile);
        textName= rootView.findViewById(R.id.display_name);
        textPass= rootView.findViewById(R.id.display_pass);


//        SharedViewModel sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
//        sharedViewModel.getEmail().observe(getViewLifecycleOwner(), this::setEmail);
//        sharedViewModel.getMobile().observe(getViewLifecycleOwner(), this::setMobile);
//        sharedViewModel.getPass().observe(getViewLifecycleOwner(), this::setPass);
//        sharedViewModel.getName().observe(getViewLifecycleOwner(), this::setName);
//        sharedViewModel.getGender().observe(getViewLifecycleOwner(), this::setGender);
//        sharedViewModel.getAge().observe(getViewLifecycleOwner(), this::setAge);
//        sharedViewModel.getWeight().observe(getViewLifecycleOwner(), this::setWeight);
//        sharedViewModel.getHeight().observe(getViewLifecycleOwner(), this::setHeight);
//        sharedViewModel.getActive().observe(getViewLifecycleOwner(), this::setActive);
        //sharedViewModel.getCalories().observe(getViewLifecycleOwner(), this::displayCalories);






        register.setOnClickListener(view -> {
            //Here register function will come use get functions for taking data eg: for gender do getGender()
            registerUser();

        });

        return rootView;
    }


//    private void registerUser() {
//
//        Email = getEmail();
//        Password = getPass();
//
//
//
//    }

//    public void displayCalories(Double calorie) {
//
//    private void displayEmail(String string) {
//        textEmail.setText(string);
//    }
//}

    private void registerUser(){
        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String email= sh.getString("email", "jamila.ansari78@gmail.com");
        String mobile= sh.getString("mobile", "9167175515");
        String pass= sh.getString("pass", "anam");
        String name= sh.getString("name", "jamila");
        String age= sh.getString("age", "18");
        String gender= sh.getString("gender", "Female");
        String height= sh.getString("height", "180");
        String weight= sh.getString("weight", "70");
        String active= sh.getString("active", "Light");

        if(active.equals("Light"))
            a= 1.2;
        else if(active.equals("Moderate"))
            a= 1.55;
        else
            a= 1.9;
        if(gender.equals("Male"))
            mcalorie= (66.5 + 13.8 * Integer.parseInt(weight) + 5* Integer.parseInt(height) ) - (6.8 * Integer.parseInt(age)) * a;
        else
            mcalorie= (655.1 + 9.5 * Integer.parseInt(weight) + 1.8* Integer.parseInt(height) ) - (4.6* Integer.parseInt(age)) * a;

        displayCalories(mcalorie);

        textEmail.setText(email);
        textPass.setText(pass);
        mAuth= FirebaseAuth.getInstance();
        String Email= textEmail.getText().toString();
        String Pass= textPass.getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(getContext(), "Email not valiied", Toast.LENGTH_LONG).show();
        }
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "Succesful", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "UnSuccesful", Toast.LENGTH_LONG).show();
                }
            }
        });
//        mAuth.createUserWithEmailAndPassword(map1.get("email"), map1.get("pass"))
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser use=mAuth.getCurrentUser();
//                            use.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    Toast.makeText(getContext(),"verification link sent",Toast.LENGTH_SHORT).show();
//
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(getContext(),"failed",Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            HashMap<String,Object> map=new HashMap<>();
//                            map.put("name",name.trim());
//                            map.put("email",email.trim());
//                            map.put("mobile",mobile.trim());
//                            //map.put("password",pass.trim());
//                            map.put("id",mAuth.getCurrentUser().getUid());
//                            map.put("height",height.trim());
//                            map.put("weight",weight.trim());
//                            map.put("age",age.trim());
//                            map.put("gender", gender.trim());
//                            map.put("activity",active.trim());
//                            map.put("calories", mcalorie.toString().trim());
//
//                            FirebaseDatabase.getInstance().getReference("USERS")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(getContext(), "user has been registerd", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        Toast.makeText(getContext(), "failed to register", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                            Intent i=new Intent(getActivity(), Login.class);
//                            startActivity(i);
//
//                        }
//                        else {
//                            Toast.makeText(getContext(), "failed to register12", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
    }


    private void displayCalories(Double calorie) {
        calories.setText(calorie.toString());
    }


}