package com.example.hpnotebook.volunteerapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class DashboardFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;

    public DashboardFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "My Events";
            case 1:
                return "New Events";
            case 2:
                return "Achieved";
            default:
                return "";
        }
    }

}
