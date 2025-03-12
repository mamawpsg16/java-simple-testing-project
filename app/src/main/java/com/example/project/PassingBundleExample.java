package com.example.project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class PassingBundleExample extends AppCompatActivity {
    TextView txtString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_passing_bundle_example);

        txtString = findViewById(R.id.txtString);

        // getting the bundle from the intent
//        Intent intent = getIntent();
//        if (intent.hasExtra("key1")) {
//            txtString.setText(intent.getStringExtra("key1"));
//        } else {
//            txtString.setText("No value from MainActivity");
//        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            txtString.setText(bundle.getString("key1", "No value from the Bundle Test Activity"));
        } else {
            txtString.setText("No bundle received");
        }
    }
}