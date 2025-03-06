package com.example.project;

import com.google.gson.Gson;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.HashMap;
import java.util.Map;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Map<String, Object> users = new HashMap<>();
    private EditText usernameField, passwordField;
    private CredentialManager credentialManager;  // Declare as a field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Initialize input fields
        usernameField = findViewById(R.id.inputUsername);
        passwordField = findViewById(R.id.inputPassword);

        credentialManager = new CredentialManager(this);
        Log.d("MAIN", new Gson().toJson(credentialManager.getCredentials()));

        TextView textRegister = findViewById(R.id.textRegister);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to RegisterActivity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void handleLogin(View view) {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Username and password cannot be empty.");
            return;
        }

        /* VALIDATE CREDENTIALS */
        boolean isCredentialExists = credentialManager.checkCredentials(username, password);
        if(!isCredentialExists){
            showAlert("Credentials do not match!");
            return;
        }

        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

        // Clear input fields
        emptyInputs();

        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);
    }

    private void showAlert(String message) {
        Alert.set(this, "Error", message);
    }

    //FOR MANUAL NOT USING SHARED PREFERENCES CHECKING OF CREDENTIALS
    public boolean checkCredentials(String username, Object password) {
        Object storedPassword = users.get(username);
        return storedPassword != null && storedPassword.toString().equals(password.toString());
    }

    private void emptyInputs() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void clearCredentials(View view) { // Ensure it's public
        CredentialManager credentialManager = new CredentialManager(this);
        Log.d("ClearCredentials", new Gson().toJson(credentialManager.getCredentials()));
        credentialManager.clearCredentials();
        Log.d("ClearCredentials", new Gson().toJson(credentialManager.getCredentials()));
        Alert.set(this, "Sucess", "Credentials Cleared Sucessfully!");
    }
}