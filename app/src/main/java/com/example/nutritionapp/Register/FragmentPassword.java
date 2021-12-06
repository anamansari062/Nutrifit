package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentPasswordBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentPassword extends Fragment {
    FragmentPasswordBinding binding;
    EditText textPass, textPassC;
    RegisterMain registerMain;
    private ExtendedFloatingActionButton next,back;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentPasswordBinding.inflate(inflater, container, false);
        View root= binding.getRoot();

        textPass= root.findViewById(R.id.text_pass);
        textPassC= root.findViewById(R.id.text_passc);
        next= root.findViewById(R.id.pass_next);
        back=root.findViewById(R.id.pass_back);

        registerMain = (RegisterMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid = true;
                if(textPass.length()==0){
                    textPass.setError("Password cannot be empty");
                    valid=false;
                }
                if(textPassC.length()==0){
                    textPassC.setError("Password cannot be empty");
                    valid=false;
                }
                if(!(textPass.getText().toString().equals( textPassC.getText().toString())))
                {
                    textPassC.setError("Passwords do not match");
                    valid = false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new GenderFragment());
//                fr.addToBackStack(null);
                    fr.commit();
                }
                sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
                if(textPass.getText().toString()!= null){
                    registerMain.myEdit.putString("pass", textPass.getText().toString());
                    registerMain.myEdit.commit();
               }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new FragmentEmail()).addToBackStack(null);
                fragmentManager.commit();


            }
        });






        return root;
    }
}