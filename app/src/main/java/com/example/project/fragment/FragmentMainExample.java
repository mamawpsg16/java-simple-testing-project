package com.example.project.fragment;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.R;

public class FragmentMainExample extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_example);

        Fragment fragment = new ExampleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("frag1", "Default Bundle From Main Activity ! - Fragment 1");
        fragment.setArguments(bundle);

        loadFragment(fragment);

        Button btnFragment1 = findViewById(R.id.handleFragment1);
        Button btnFragment2 = findViewById(R.id.handleFragment2);

        btnFragment1.setOnClickListener(v ->{
            Fragment fragment1 = new ExampleFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putString("frag1", "Hello From Main Activity! - Fragment 1");
            fragment1.setArguments(bundle1);
            loadFragment(fragment1);
        });

        btnFragment2.setOnClickListener(v ->{
            Fragment fragment1 = new ExampleFragment2();
            Bundle bundle1 = new Bundle();
            bundle1.putString("frag2", "Hello From Main Activity! - Fragment 2");
            fragment1.setArguments(bundle1);
            loadFragment(fragment1);
        });

    }

    private void loadFragment(Fragment fragment){
        // Get FragmentManager and begin transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace container with fragment
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
