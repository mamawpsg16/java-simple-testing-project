package com.example.project.array_adapter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapaterMainActivity extends AppCompatActivity {

    // Declare a ListView to display the list of courses
    ListView simpleListView;

    // Array of course names that will be displayed in the ListView
    String courseList[] = {"C-Programming", "Data Structure", "Database", "Python",
            "Java", "Operating System", "Compiler Design", "Android Development"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the activity layout from XML file
        setContentView(R.layout.activity_array_adapater_main);

        // Initialize the ListView by finding it from the layout file
        simpleListView = (ListView) findViewById(R.id.simpleListView);

        // Create an ArrayAdapter to bind the courseList array to the ListView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,                          // Context (current activity)
                R.layout.array_adapter_item_view,  // Layout file for each list item
                R.id.itemTextView,              // TextView ID inside the item layout
                courseList                      // Data source (array of courses)
        );

        // Set the adapter to the ListView so it displays the items
        simpleListView.setAdapter(arrayAdapter);

        // Apply window insets to adjust padding for system bars (like status and navigation bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
