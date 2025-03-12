package com.example.project.fragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;
import androidx.viewpager2.widget.ViewPager2;

public class SwipeFragmentMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_swipe_fragment_main);

        // Find the ViewPager2 in the layout
        ViewPager2 viewPager = findViewById(R.id.viewpager);

        // Create the adapter
        SwipeFragmentStateAdapter adapter = new SwipeFragmentStateAdapter(this);

        // Attach the adapter to the ViewPager
        viewPager.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}