package com.example.nutritionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeightFragment extends Fragment {
    TextView weight;
    ImageView incweight,decweight;

    int weight1=70;
    String weight2="70";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeightFragment newInstance(String param1, String param2) {
        WeightFragment fragment = new WeightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_weight, container, false);
        weight=rootView.findViewById(R.id.fragment_weight_currentWeight);
        incweight=rootView.findViewById(R.id.fragment_weight_incrementWeight);
        decweight=rootView.findViewById(R.id.fragment_weight_decrementWeight);
        incweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight1+=1;
                weight2=String.valueOf(weight1);
                weight.setText(weight2);
            }
        });

        decweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weight1>0) {
                    weight1 -= 1;
                    weight2 = String.valueOf(weight1);
                    weight.setText(weight2);
                }
            }
        });

        return rootView;
    }
}