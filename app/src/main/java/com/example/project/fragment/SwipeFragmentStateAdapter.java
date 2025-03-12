package com.example.project.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SwipeFragmentStateAdapter extends FragmentStateAdapter {

    public SwipeFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SwipeFragment1();
            case 1:
                return new SwipeFragment2();
            default:
                return new SwipeFragment3();
        }
    }
}