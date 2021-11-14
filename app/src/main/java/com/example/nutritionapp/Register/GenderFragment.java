package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.nutritionapp.R;


public class GenderFragment extends Fragment {

    ;
    RelativeLayout male,female;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gender, container, false);
//        male = rootView.findViewById(R.id.malecard);
//        female = rootView.findViewById(R.id.femalecard);
//        trans = rootView.findViewById(R.id.transcard);
        male = rootView.findViewById(R.id.fragment_gender_maleLogo);
        female = rootView.findViewById(R.id.fragment_gender_femaleLogo);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                female.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));

            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                male.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));

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