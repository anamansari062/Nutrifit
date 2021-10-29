package com.example.nutritionapp.ui.update;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentUpdateBinding;

import java.util.ArrayList;

public class UpdateFragment extends Fragment {

    private UpdateViewModel updateViewModel;
    private FragmentUpdateBinding binding;
    Button mcalculate;
    TextView mcurrentheight;
    TextView mcurrentage,mcurrentweight;
    ImageView mincrementage,mincrementweight,mdecrementage,mdecrementweight;
    SeekBar mheightbar;
    RelativeLayout mmale,mfemale;
   Spinner spinner;
//    AutoCompleteTextView auto_activity;
    ArrayList<String> arrayList_update;
    ArrayAdapter arrayAdapter_update;

    int currentProgress;
    String mintProgress="160";
    int age=18;
    String age2="18";
    int weight=70;
    String weight2="70";
    String typeOfUser="0";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        updateViewModel = new ViewModelProvider(this).get(UpdateViewModel.class);

        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mcalculate = root.findViewById(R.id.fragment_update_updateBtn);
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
        arrayList_update = new ArrayList<>();
        arrayList_update.add(0,"How active are you?") ;
        arrayList_update.add("Low Physical Activity");
        arrayList_update.add("Average Physical Activity");
        arrayList_update.add("High Physical Activity");


        arrayAdapter_update = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,arrayList_update);
        spinner.setAdapter(arrayAdapter_update);
//        auto_activity.setThreshold(1);
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
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
//                    ((TextView) adapterView.getChildAt(0)).setTextSize(5);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                mfemale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                typeOfUser="MALE";
            }
        });

        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfemale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                mmale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                typeOfUser="FEMALE";
            }
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

        mincrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age+=1;
                age2=String.valueOf(age);
                mcurrentage.setText(age2);
            }
        });
        mincrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight+=1;
                weight2=String.valueOf(weight);
                mcurrentweight.setText(weight2);
            }
        });
        mdecrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age-=1;
                age2=String.valueOf(age);
                mcurrentage.setText(age2);
            }
        });
        mdecrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight-=1;
                weight2=String.valueOf(weight);
                mcurrentweight.setText(weight2);
            }
        });

        mcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
//                    Intent intent = new Intent(UpdateFragment.this.getActivity(), MainBMI.class);
//                    intent.putExtra("Gender",typeOfUser);
//                    intent.putExtra("Height",mintProgress);
//                    intent.putExtra("Weight",weight2);
//                    intent.putExtra("Age",age2);
//                    startActivity(intent);
                    Toast.makeText(getContext(), "Your details have been updated successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });


//        final TextView textView = binding.textCalculate;
        updateViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);

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