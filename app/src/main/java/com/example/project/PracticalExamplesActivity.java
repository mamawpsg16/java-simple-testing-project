package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalExamplesActivity extends AppCompatActivity {

    ToggleButton togglebutton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_practical_examples);

//        RADIO BUTTON
//        Button submit = findViewById(R.id.submit);
//        Button clear = findViewById(R.id.clear);
//        RadioGroup radioGroup = findViewById(R.id.groupradio);

        // Uncheck or reset theradio buttons initially
//        radioGroup.clearCheck();

        // Add the Listener to the RadioGroup
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId != -1) { // Ensure a valid selection
//                RadioButton radioButton = group.findViewById(checkedId);
//                if (radioButton != null) { // Double-check to avoid crashes
//                    Toast.makeText(this, "Selected: " + checkedId + " = " + radioButton.getText(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


//        submit.setOnClickListener(v -> {
//            int selectedId = radioGroup.getCheckedRadioButtonId();
//            if (selectedId == -1) {
//                Toast.makeText(this, "No answer has been selected",
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                RadioButton radioButton = findViewById(selectedId);
//                // Now display the value of selected item
//                // by the Toast message
//                Toast.makeText(this, radioButton.getText(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        // Add the Listener to the Submit Button
//        clear.setOnClickListener(v -> {
//            radioGroup.clearCheck();
//        });


        // TOGGLE BUTTON
        togglebutton = (ToggleButton) findViewById(R.id.button_toggle);
        textView = (TextView) findViewById(R.id.textView1);

    }

    // Method is called when the toggle button is clicked
    public void onToggleClick(View view) {
        if (togglebutton.isChecked()) {
            textView.setText("Toggle is ON");
        } else {
            textView.setText("Toggle is OFF");
        }
    }
}