package com.example.project.alert_dialog;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.project.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
public class CustomDialogWithEditText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_dialog_with_edit_text);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            // Create an alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(CustomDialogWithEditText.this);
            builder.setTitle("Name");

            // Set the custom layout
            LayoutInflater inflater = getLayoutInflater();
            View customLayout = inflater.inflate(R.layout.dialog_custom_layout, null);
            builder.setView(customLayout);

            // Add a button
            builder.setPositiveButton("OK", (dialog, which) -> {
                // Send data from the AlertDialog to the Activity
                EditText editText = customLayout.findViewById(R.id.editText);
                Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
            });

            // Create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}