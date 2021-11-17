package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentNameBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NameFragment extends Fragment {
    private FragmentNameBinding binding;
    private SharedViewModel sharedViewModel;
    RegisterMain registerMain;
    private ExtendedFloatingActionButton next,back;
    EditText username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentNameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        username = root.findViewById(R.id.text_name);
        next= root.findViewById(R.id.name_next);
        back=root.findViewById(R.id.name_back);

        registerMain = (RegisterMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid=true;
                if(username.length()==0)
                {
//            Toast.makeText(getContext(), "F", Toast.LENGTH_SHORT).show();
                    username.setError("Name is required");
                    valid=false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new GenderFragment());
//                fr.addToBackStack(null);
                    fr.commit();
                    registerMain.myEdit.putString("name", username.getText().toString());
                    registerMain.myEdit.commit();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new FragmentPassword()).addToBackStack(null);
                fragmentManager.commit();

            }
        });


        return root;

    }
}