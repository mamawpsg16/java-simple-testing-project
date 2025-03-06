package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize input fields
        usernameField = findViewById(R.id.inputRegisterUsername);
        passwordField = findViewById(R.id.inputRegisterPassword);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void handleRegister(View view) {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Username and password cannot be empty.");
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

        /* SAVE CREDENTIALS */
        CredentialManager credentialManager = new CredentialManager(this);
        credentialManager.saveCredentials(username, password);

        Log.d("SharedPreferences", new Gson().toJson(credentialManager.getCredentials()));

        Toast.makeText(this, "Registration Successful, Redirecting to Login!", Toast.LENGTH_LONG).show();

        // Clear input fields
        emptyInputs();

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    private void showAlert(String message) {
        Alert.set(this, "Error", message);
    }

    private void emptyInputs() {
        usernameField.setText("");
        passwordField.setText("");
    }
}