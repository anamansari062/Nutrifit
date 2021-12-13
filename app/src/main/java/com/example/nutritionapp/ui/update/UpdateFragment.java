package com.example.nutritionapp.ui.update;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentUpdateBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class UpdateFragment extends Fragment {

    private UpdateViewModel updateViewModel;
    private FragmentUpdateBinding binding;
    Button mcalculate;
    TextView mcurrentheight;
    TextView mcurrentage,mcurrentweight;
    ImageView mincrementage,mincrementweight,mdecrementage,mdecrementweight;
    SeekBar mheightbar;
    RelativeLayout mmale,mfemale;
    EditText editName;
    Spinner spinner;
    Double a;
    String name;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList_activity;
    ArrayAdapter<String> arrayAdapter_activity;

    int currentProgress;
    String mintProgress="160";
    int age;
    String age2;
    int weight;
    String weight2;
    String typeOfUser="0";
    double calorie;
    int heightc,weightc,agec;
    String value;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mcalculate = root.findViewById(R.id.fragment_update_UpdateBtn);
        mcurrentage=root.findViewById(R.id.fragment_update_currentAge);
        mcurrentheight=root.findViewById(R.id.fragment_update_currentHeight);
        mcurrentweight= root.findViewById(R.id.fragment_update_currentWeight);
        mincrementweight=root.findViewById(R.id.fragment_update_incrementWeight);
        mincrementage=root.findViewById(R.id.fragment_update_incrementAge);
        mdecrementage=root.findViewById(R.id.fragment_update_decrementAge);
        mdecrementweight=root.findViewById(R.id.fragment_update_decrementWeight);
        mheightbar=root.findViewById(R.id.fragment_update_heightBar);
        mmale=root.findViewById(R.id.fragment_update_maleLogo);
        mfemale=root.findViewById(R.id.fragment_update_femaleLogo);
        spinner=root.findViewById(R.id.fragment_update_spinner1);
        editName= root.findViewById(R.id.editTextTextPersonName);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("USERS");
        if(user !=null){
            databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Map<String, String> map= (Map<String, String>) snapshot.getValue();
                    age2=map.get("age");
                    age=Integer.parseInt(age2);
                    mcurrentage.setText(age2);
                    weight2=map.get("weight");
                    weight=Integer.parseInt(weight2);
                    mcurrentweight.setText(weight2);
                    mintProgress=map.get("height");
                    currentProgress=Integer.parseInt(mintProgress);
                    mcurrentheight.setText(mintProgress);
                    name= map.get("name");
                    editName.setText(name);

                    value=map.get("activity");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        arrayList_activity = new ArrayList<>();
        arrayList_activity.add(0,"How active are you?") ;
        arrayList_activity.add("Low Physical Activity");
        arrayList_activity.add("Average Physical Activity");
        arrayList_activity.add("High Physical Activity");


        arrayAdapter_activity = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,arrayList_activity);
        arrayAdapter_activity.setDropDownViewResource(R.layout.custom_selected_spinner_layout);
        spinner.setAdapter(arrayAdapter_activity);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("How active are you?"))
                {
                    //do nothing
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
                }
                else
                {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                     value = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mmale.setOnClickListener(view -> {
            mmale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
            mfemale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            typeOfUser="Male";
        });

        mfemale.setOnClickListener(view -> {
            mfemale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
            mmale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            typeOfUser="Female";
        });

        mheightbar.setMax(300);
        mheightbar.setProgress(160);
        mheightbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentProgress=i;
                mintProgress=String.valueOf(currentProgress);
                mcurrentheight.setText(mintProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mincrementage.setOnClickListener(view -> {
            age+=1;
            age2=String.valueOf(age);
            mcurrentage.setText(age2);
        });
        mincrementweight.setOnClickListener(view -> {
            weight+=1;
            weight2=String.valueOf(weight);
            mcurrentweight.setText(weight2);
        });
        mdecrementage.setOnClickListener(view -> {
            if(age>0) {
                age -= 1;
                age2 = String.valueOf(age);
                mcurrentage.setText(age2);
            }
        });
        mdecrementweight.setOnClickListener(view -> {
            if(weight>0) {
                weight -= 1;
                weight2 = String.valueOf(weight);
                mcurrentweight.setText(weight2);
            }
        });




        mcalculate.setOnClickListener(view -> {
            if(typeOfUser.equals("0"))
            {
                Toast.makeText(getContext(), "Select Your Gender", Toast.LENGTH_SHORT).show();
            }
            else if(mintProgress.equals("0"))
            {
                Toast.makeText(getContext(), "Select Your Height", Toast.LENGTH_SHORT).show();
            }
            else if(age==0 || age<0)
            {
                Toast.makeText(getContext(), "Age is incorrect", Toast.LENGTH_SHORT).show();
            }
            else
            {
                calorie= calculate(Integer.parseInt(mcurrentheight.getText().toString()), Integer.parseInt(mcurrentweight.getText().toString()),Integer.parseInt(mcurrentage.getText().toString()), typeOfUser, value);
                databaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("age").setValue(age2);
                        snapshot.getRef().child("activity").setValue(value);
                        String Calories=Double.toString(calorie);
                        snapshot.getRef().child("name").setValue(editName.getText().toString());

                        snapshot.getRef().child("calories").setValue(Calories);
                        snapshot.getRef().child("gender").setValue(typeOfUser);
                        snapshot.getRef().child("height").setValue(mintProgress);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                alertDialog();
            }

        });


        return root;
    }

    private double calculate(int height, int weight, int age, String gender, String active) {
        Double calorie;
        if(active.equals("Light"))
            a= 1.2;
        else if(active.equals("Moderate"))
            a= 1.55;
        else
            a= 1.9;
        if(gender.equals("Male"))
            calorie= (66.5 + (13.8 * weight) + (5* height) ) - (6.8 * age) * a;
        else
            calorie= (655.1 + (9.5 * weight) + (1.8* height) ) - (4.6* age) * a;
        return calorie;

    }
    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());

            dialog.setMessage("Your details have been updated successfully , The number of " +
                    "calories you need are : " + calorie + " cal");

        dialog.setTitle("Successful");
        dialog.setPositiveButton("Ok",
                (dialog1, which) -> {
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
                    }
                }
