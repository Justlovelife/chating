package com.example.chating.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class SectionPagerAdapter extends FragmentPagerAdapter {


    private  List<Fragment>fragments;
    public  SectionPagerAdapter(FragmentManager fragmentManager, List<Fragment>fragments){
         super(fragmentManager);
         this.fragments=fragments;
    }
    @NonNull
    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
