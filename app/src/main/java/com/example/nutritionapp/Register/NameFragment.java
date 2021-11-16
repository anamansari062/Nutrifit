package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentNameBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NameFragment extends Fragment {
    private FragmentNameBinding binding;
    private SharedViewModel sharedViewModel;
    private FloatingActionButton next;
    EditText username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentNameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        username = root.findViewById(R.id.editTextTextPersonName);
        next= root.findViewById(R.id.name_next);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString()==null)
                {
//            Toast.makeText(getContext(), "F", Toast.LENGTH_SHORT).show();
                    username.setError("name is required");
                }
                else{
//                    sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
//                    sharedViewModel.setName(username.getText().toString());
                    myEdit.putString("name", username.getText().toString());
                    myEdit.commit();
                }
            }
        });


        return root;

    }
}