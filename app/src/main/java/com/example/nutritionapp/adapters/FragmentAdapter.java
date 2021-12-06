//package com.example.nutritionapp.adapters;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.lifecycle.Lifecycle;
//import androidx.viewpager2.adapter.FragmentStateAdapter;
//
//import com.example.nutritionapp.Register.ActiveFragment;
//import com.example.nutritionapp.Register.AgeFragment;
//import com.example.nutritionapp.Register.FragmentCalorie;
//import com.example.nutritionapp.Register.FragmentEmail;
//import com.example.nutritionapp.Register.FragmentPassword;
//import com.example.nutritionapp.Register.GenderFragment;
//import com.example.nutritionapp.Register.HeightFragment;
//import com.example.nutritionapp.Register.WeightFragment;
//
//public class FragmentAdapter extends FragmentStateAdapter {
//    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
//        super(fragmentManager, lifecycle);
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        switch (position)
//        {
//
//            case 1:
//                return new FragmentPassword();
//            case 2:
//                return new NameFragment();
//            case 3:
//                return new GenderFragment();
//            case 4:
//                return new AgeFragment();
//            case 5:
//                return new WeightFragment();
//            case 6:
//                return new HeightFragment();
//            case 7:
//                return new ActiveFragment();
//            case 8:
//                return new FragmentCalorie();
//
//        }
//        return new FragmentEmail();
//    }
//
//    @Override
//    public int getItemCount() {
//        return 9;
//    }
//}
