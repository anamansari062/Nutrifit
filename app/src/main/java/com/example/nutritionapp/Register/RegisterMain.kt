package com.example.nutritionapp.Register

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.nutritionapp.R
import com.google.android.material.tabs.TabLayout

class RegisterMain : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager2: ViewPager2? = null
    var myEdit: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_main)
        val i = intent
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.register_container, FragmentEmail())
        fragmentTransaction.commit()
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        myEdit = sharedPreferences.edit()

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