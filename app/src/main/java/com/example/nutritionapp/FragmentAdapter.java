package com.example.nutritionapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:
                return new GenderFragment();
            case 2:
                return new AgeFragment();
            case 3:
                return new WeightFragment();
            case 4:
                return new HeightFragment();
            case 5:
                return new ActiveFragment();

        }
        return new NameFragment();
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
