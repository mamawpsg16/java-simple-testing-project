package com.example.project.spinner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;


public class CustomSpinner extends AppCompatActivity {

    ArrayList<CustomSpinnerItem> CustomSpinnerItems;
    CustomSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_spinner);

        initList();
        Spinner spinner = findViewById(R.id.spinner_algorithm);

        // we pass our item list and context to our Adapter.
        adapter = new CustomSpinnerAdapter(this, CustomSpinnerItems);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id)
                    {
                        // It returns the clicked item.
                        CustomSpinnerItem clickedItem = (CustomSpinnerItem)parent.getItemAtPosition(position);

                        String name = clickedItem.getName();

                        Toast.makeText(CustomSpinner.this,name + " selected",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {}
                });
    }

    // It is used to set the algorithm names to our array
    // list.
    private void initList()
    {
        CustomSpinnerItems = new ArrayList<>();
        CustomSpinnerItems.add(new CustomSpinnerItem("Quick Sort"));
        CustomSpinnerItems.add(new CustomSpinnerItem("Merge Sort"));
        CustomSpinnerItems.add(new CustomSpinnerItem("Heap Sort"));
        CustomSpinnerItems.add(new CustomSpinnerItem("Prims Algorithm"));
        CustomSpinnerItems.add(new CustomSpinnerItem("Kruskal Algorithm"));
        CustomSpinnerItems.add(new CustomSpinnerItem("Rabin Karp"));
        CustomSpinnerItems.add(new CustomSpinnerItem("Binary Search"));
    }
}