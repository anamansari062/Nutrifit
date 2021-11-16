package com.example.nutritionapp.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.nutritionapp.adapters.FragmentAdapter;
import com.example.nutritionapp.R;
import com.google.android.material.tabs.TabLayout;

public class RegisterMain extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);
        Intent i = getIntent();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.register_container,new FragmentEmail());
        fragmentTransaction.commit();

//        tabLayout = findViewById(R.id.tab_layout);
//        pager2 = findViewById(R.id.view_pager2);
//
//        FragmentManager fm = getSupportFragmentManager();
//        adapter = new FragmentAdapter(fm,getLifecycle());
//        pager2.setAdapter(adapter);
//
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//        tabLayout.addTab(tabLayout.newTab().setText(""));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                pager2.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                tabLayout.selectTab(tabLayout.getTabAt(position));
//            }
//        });
    }
}