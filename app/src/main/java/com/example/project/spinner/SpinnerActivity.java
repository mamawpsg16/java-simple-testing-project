package com.example.project.spinner;

import android.os.Bundle;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.project.R;

public class SpinnerActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    // Create array of Strings and store the names of courses
    private String[] courses = {
            "C", "Data structures",
            "Interview prep", "Algorithms",
            "DSA with java", "OS"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spinner);

        // Take the instance of Spinner and apply OnItemSelectedListener on it
        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter having the list of courses
        ArrayAdapter<String> ad = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                courses
        );

        // Set simple layout resource file for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
        spin.setAdapter(ad);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Make toast of the name of the course which is selected in the spinner
        Toast.makeText(getApplicationContext(), courses[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No action needed when no selection is made
    }
}