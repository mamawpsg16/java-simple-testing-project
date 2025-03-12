package com.example.project.alert_dialog;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

public class AlertDialogMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alert_dialog_main);
    }

    // Declare the onBackPressed method when the
    // back button is pressed this method will call
    @Override
    public void onBackPressed() {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogMainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit?");

        // Set Alert Title
        builder.setTitle("Alert!");

        // Set Cancelable false so the dialog cannot be dismissed by clicking outside
        builder.setCancelable(false);

        // Set the positive button with "Yes"
        builder.setPositiveButton("Yes", (dialog, which) -> {
            super.onBackPressed(); // Call super method to handle the default behavior to go back to the prev activity or close the app
        });

        // Set the negative button with "No"
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.cancel(); // Dismiss the dialog if "No" is pressed
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}