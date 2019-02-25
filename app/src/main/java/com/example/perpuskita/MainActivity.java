package com.example.perpuskita;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //deklarasi variabel
    private TabLayout tabs;
    private ViewPager pager;

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //connect to activity_main.xml (resource ui dan id)
        setContentView(R.layout.activity_main);

        //inisialisasi sesuai id yg ada di activity_main yang tipenya sesuai deklarasi variabel
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);

        //inisialisasi viewpageradapter
        ViewPagerAdapter myPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add titles to each tab
        myPagerAdapter.addFragment(new BorrowFragment(),null);
        myPagerAdapter.addFragment(new BooksFragment(),null);
        myPagerAdapter.addFragment(new ProfileFragment(), null);

        //add adapter to pager
        pager.setAdapter(myPagerAdapter);

        //connect tab with pager
        tabs.setupWithViewPager(pager);

        //styling tab
        tabs.setTabTextColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setTabMode(TabLayout.MODE_FIXED);

        int[] tabIcon = {
                R.drawable.ic_book_24dp,
                R.drawable.ic_search_24dp,
                R.drawable.ic_person_24dp
        };

        for (int i = 0; i<tabIcon.length;i++){
            TabLayout.Tab tab = tabs.getTabAt(i);
            if (tab != null) tab.setIcon(tabIcon[i]);
        }
    }

}
