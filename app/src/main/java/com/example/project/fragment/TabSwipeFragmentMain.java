package com.example.project.fragment;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.project.R;

public class TabSwipeFragmentMain extends AppCompatActivity {

    // Declare an adapter for managing fragments in ViewPager2
    private TabSwipeAdapter tabSwipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge display for better UI experience
        EdgeToEdge.enable(this);

        // Set the activity layout
        setContentView(R.layout.activity_tab_swipe_fragment_main);

        // Find ViewPager2 and TabLayout in the XML layout
        ViewPager2 viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        // Initialize the adapter and pass this activity as the context
        tabSwipeAdapter = new TabSwipeAdapter(this);

        // Add fragments with corresponding titles
        tabSwipeAdapter.addFragment(new SwipeFragment1(), "Page 1");
        tabSwipeAdapter.addFragment(new SwipeFragment2(), "Page 2");
        tabSwipeAdapter.addFragment(new SwipeFragment3(), "Page 3");

        // Set the adapter to ViewPager2, allowing swiping between fragments
        viewPager.setAdapter(tabSwipeAdapter);

        // Link TabLayout with ViewPager2 using TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText(tabSwipeAdapter.getPageTitle(position)) // Set tab text from adapter
        ).attach();
    }
}
