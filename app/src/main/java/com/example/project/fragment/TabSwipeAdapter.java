package com.example.project.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabSwipeAdapter extends FragmentStateAdapter {

    // List to store fragments for each tab
    private final List<Fragment> fragments = new ArrayList<>();

    // List to store titles for each tab
    private final List<String> fragmentTitles = new ArrayList<>();

    // Constructor: Takes a FragmentActivity to associate the adapter with ViewPager2
    public TabSwipeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // Method to add a fragment and its title to the lists
    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    // Returns the number of fragments in the adapter
    @Override
    public int getItemCount() {
        return fragments.size();
    }

    // Returns the fragment corresponding to a given position (index)
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    // Returns the title of the tab at a specific position
    public String getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
