package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentEmailBinding;
import com.example.nutritionapp.databinding.FragmentGenderBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class GenderFragment extends Fragment {
    private FragmentGenderBinding binding;
    String typeOfUser="";
    private ExtendedFloatingActionButton next,back;
    private SharedViewModel sharedViewModel;
    RelativeLayout male,female;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGenderBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
//        male = rootView.findViewById(R.id.malecard);
//        female = rootView.findViewById(R.id.femalecard);
//        trans = rootView.findViewById(R.id.transcard);
        male = rootView.findViewById(R.id.fragment_gender_maleLogo);
        female = rootView.findViewById(R.id.fragment_gender_femaleLogo);
        next= rootView.findViewById(R.id.gender_next);
        back=rootView.findViewById(R.id.gender_back);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                female.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                typeOfUser= "Male";

            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                male.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                typeOfUser= "Female";

            }
        });

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
//                sharedViewModel.setGender(typeOfUser);
                Boolean valid=true;
                if(typeOfUser.equals(""))
                {
                    Toast.makeText(getContext(), "This field cannot be empty", Toast.LENGTH_SHORT).show();
                    valid=false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new AgeFragment());
//                fr.addToBackStack(null);
                    fr.commit();
                }
                myEdit.putString("gender", typeOfUser);
                myEdit.commit();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new NameFragment()).addToBackStack(null);
                fragmentManager.commit();

            }
        });



//        male.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    // set color here
//                    male.setCardBackgroundColor(R.drawable.malefemalefocus);
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    // set other color here
//
//                }
//                return true;
//            }
//        });
                return rootView;
    }
}