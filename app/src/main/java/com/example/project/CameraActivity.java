package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {

    // Declare button and image view variables
    Button camera_open_id;  // Button to open the camera
    ImageView click_image_id;  // ImageView to display the captured image

    // ActivityResultLauncher for handling camera result
    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                // Check if the result was successful
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();  // Retrieve the intent data
                    if (data != null && data.getExtras() != null) { // Ensure data is not null
                        Bitmap photo = (Bitmap) data.getExtras().get("data"); // Extract the image as a Bitmap
                        if (photo != null) {
                            click_image_id.setImageBitmap(photo); // Set the image in ImageView
                        } else {
                            showToast("Failed to capture image."); // Show error message if photo is null
                        }
                    } else {
                        showToast("No image data received."); // Show message if no data is returned
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera); // Set the layout file for this activity

        // Initialize UI components by finding their IDs from the XML layout
        camera_open_id = findViewById(R.id.camera_button); // Button to open the camera
        click_image_id = findViewById(R.id.click_image); // ImageView to display captured image

        // Set a click listener on the button to open the camera
        camera_open_id.setOnClickListener(v -> {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Create intent to open the camera
            cameraLauncher.launch(camera_intent); // Launch the camera activity
        });
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
