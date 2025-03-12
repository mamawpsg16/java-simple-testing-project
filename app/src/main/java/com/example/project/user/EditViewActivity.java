package com.example.project.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.database.AppDatabase;
import com.example.project.database.entities.User;
import com.google.android.material.textfield.TextInputEditText;
import com.example.project.database.dao.UserDao;
import org.mindrot.jbcrypt.BCrypt;

public class EditViewActivity extends AppCompatActivity {
    TextInputEditText inputUsername, inputEmailAddress, inputCurrentPassword, inputNewPassword, inputNewPasswordConfirmation;
    String originalUsername;
    Button btnUpdate;
    AppDatabase db;
    UserDao userDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        db = AppDatabase.getInstance(this);
        userDao = db.userDao();

        // Initialize views
        inputUsername = findViewById(R.id.inputEditUserUsername);
        inputEmailAddress = findViewById(R.id.inputEditUserEmailAddress);
        inputCurrentPassword = findViewById(R.id.inputEditUserOldPassword);
        inputNewPassword = findViewById(R.id.inputEditUserNewPassword);
        inputNewPasswordConfirmation = findViewById(R.id.inputEditUserConfirmPassword);
        btnUpdate = findViewById(R.id.updateUserDetails);

        // Retrieve the user data passed from the adapter
        Intent intent = getIntent();
        originalUsername = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");  // Retrieve email


        // Set the retrieved data to the input fields
        if (originalUsername != null) {
            inputUsername.setText(originalUsername);
        }
        if (email != null) {
            inputEmailAddress.setText(email);
        }
    }

    public void handleUpdateUser(View view) {
        String newUsername = inputUsername.getText().toString().trim();
        String newEmail = inputEmailAddress.getText().toString().trim();
        String currentPassword = inputCurrentPassword.getText().toString().trim();  // Get current password
        String newPassword = inputNewPassword.getText().toString().trim();  // New password input
        String confirmPassword = inputNewPasswordConfirmation.getText().toString().trim();  // Confirm password input

        Log.d("HANDLE UPDATE", newUsername);
        Log.d("HANDLE UPDATE", newEmail);
        Log.d("HANDLE UPDATE", currentPassword);
        Log.d("HANDLE UPDATE", newPassword);
        Log.d("HANDLE UPDATE", confirmPassword);
        // Validate inputs
        if (newUsername.isEmpty() || newEmail.isEmpty()) {
            showToast("Username and email cannot be empty.");
            return;
        }
        if (newUsername.length() < 5) {
            showToast("Username must be at least 6 characters.");
            return;
        }
        if (!newPassword.isEmpty()) {
            if (newPassword.length() < 6) {
                showToast("Password must be at least 6 characters.");
                return;
            }
            if (!newPassword.equals(confirmPassword)) {
                showToast("Passwords do not match.");
                return;
            }
        }

        new Thread(() -> {
            // Retrieve user from the database
           User existingUser = userDao.getUserByUsername(originalUsername);

            if (existingUser == null) {
                runOnUiThread(() -> showToast("User not found!"));
                return;
            }

            // **Check if the entered current password matches the stored password**
            if (!BCrypt.checkpw(currentPassword, existingUser.getPassword())) {
                runOnUiThread(() -> showToast("Incorrect current password!"));
                return;
            }

            // Update username and email
            existingUser.setUsername(newUsername);
            existingUser.setEmailAddress(newEmail);

            // If a new password is entered, hash and update it
            if (!newPassword.isEmpty()) {
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
                existingUser.setPassword(hashedPassword);
            }

            // Update the user in the database
            userDao.update(existingUser);

            runOnUiThread(() -> {
                showToast("User updated successfully!");
                finish();  // Close activity after update
            });
        }).start();
    }

    private void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }
}
