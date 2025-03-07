package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.auth.AuthManager;
import com.example.project.auth.SessionManager;
import com.example.project.database.entities.User;
public class MainActivity extends AppCompatActivity {
    private EditText usernameField, passwordField;
    private AuthManager authManager;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this); // Initialize SessionManager

        if (sessionManager.isLoggedIn()) {
            // User is already logged in, go to DashboardActivity
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish(); // Prevent MainActivity from showing again
        }

        setContentView(R.layout.activity_main);

        // Initialize UI components
        usernameField = findViewById(R.id.inputUsername);
        passwordField = findViewById(R.id.inputPassword);

        // Initialize AuthManager
        authManager = new AuthManager(this);

        //Put Click Listener in Register Text View
        TextView textRegister = findViewById(R.id.textRegister);
        textRegister.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });


    }

    /**
     * Handles the login button click
     */
    public void handleLogin(View view) {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Username and password cannot be empty.");
            return;
        }

        authManager.login(username, password, new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> showAlert(errorMessage));
            }
        });
    }

    private void showAlert(String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        authManager.shutdown(); // Clean up executor service
    }
}
