package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.util.Map;

import com.example.project.database.AppDatabase;
import com.example.project.database.dao.UserDao;
import com.example.project.database.entities.User;

import org.mindrot.jbcrypt.BCrypt;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameField, emailAddressField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize input fields
        usernameField = findViewById(R.id.inputRegisterUsername);
        passwordField = findViewById(R.id.inputRegisterPassword);
        emailAddressField = findViewById(R.id.inputRegisterEmail);

        TextView textRegister = findViewById(R.id.textLogin);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to RegisterActivity
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void handleRegister(View view) {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String emailAddress = emailAddressField.getText().toString().trim();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() || emailAddress.isEmpty()) {
            showAlert("Username, Email Address and password cannot be empty.");
            return;
        }

        // Validate password
        if (username.length() < 5) {
            showAlert("Username must be equal or greater than 6 characters");
            return;
        } else if (password.length() < 5) {
            showAlert("Password must be equal or greater than 6 characters");
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);
        UserDao userDao = db.userDao();

        /* INITIALIZE CREDENTIAL MANAGER */
//        CredentialManager credentialManager = new CredentialManager(this);

//        // Check if the username already exists
//        Map<String, String> savedCredentials = credentialManager.getCredentials();
//        if (savedCredentials.containsKey(username)) {
//            showAlert("This username is already taken. Please choose another one.");
//            return;
//        }
//
//
//        credentialManager.saveCredentials(username, password);
//
//        Log.d("SharedPreferences", new Gson().toJson(credentialManager.getCredentials()));

        // Start a new background thread to perform database operations
        new Thread(() -> {
            // Check if the user already exists in the database
            User existingUser = userDao.getUserByUsername(username);
            if (existingUser != null) {
                Log.d("RegisterActivity", "User exists: " + existingUser.toString());
            } else {
                Log.d("RegisterActivity", "User not found in the database.");
            }

            if (existingUser != null) {
                // If the user exists, show an alert message on the UI thread
                runOnUiThread(() -> showAlert("This username is already taken."));
                return;
            }

            // 🔒 Hash the password before saving
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

            // If the user does not exist, insert the new user into the database
            userDao.insert(new User(username, hashedPassword, emailAddress));

            // Switch back to the UI thread to show a success message and navigate to MainActivity
            runOnUiThread(() -> {
                Toast.makeText(this, "Registration Successful, Redirecting to Login!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            });
        }).start(); // Start the background thread

        // Clear input fields
//        emptyInputs();
//
//        Intent intent = new Intent(this, MainActivity.class);
//
//        startActivity(intent);
    }

    private void showAlert(String message) {
        Alert.set(this, "Error", message);
    }
}