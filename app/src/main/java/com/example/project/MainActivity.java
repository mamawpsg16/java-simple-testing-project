package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.auth.AuthManager;
import com.example.project.auth.SessionManager;
import com.example.project.database.entities.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private EditText usernameField, passwordField;
    private AuthManager authManager;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String result = readJsonFromRaw(this,  R.raw.users);
        Log.d("RAW JSON RESULT", result);

        System.out.println("result = "+ result);
        sessionManager = new SessionManager(this); // Initialize SessionManager

        Log.d("ACTIVITY LIFECYCLE", "onCreate Called");

        if (sessionManager.isLoggedIn()) {

            // User is already logged in, go to DashboardActivity
            Intent intent = new Intent(this, DashboardActivity.class);
            // creating a bundle object
            Bundle bundle = new Bundle();

            // storing the string value in the bundle
            // which is mapped to key
            bundle.putString("key1", "GFG :- Main Activity");

            // passing the bundle into the intent
            intent.putExtras(bundle);
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

    @Override
    protected void onStart()
    {
        super.onStart();
        // It will show a message on the screen
        // then onStart is invoked
        Log.d("ACTIVITY LIFECYCLE", "onStart Called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // It will show a message on the screen
        // then onRestart is invoked
        Log.d("ACTIVITY LIFECYCLE", "onRestart Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // It will show a message on the screen
        // then onResume is invoked
        Log.d("ACTIVITY LIFECYCLE", "onResume Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // It will show a message on the screen
        // then onPause is invoked
        Log.d("ACTIVITY LIFECYCLE", "onPause Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // It will show a message on the screen
        // then onStop is invoked
        Log.d("ACTIVITY LIFECYCLE", "onStop Called");
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
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear Login from history
                    startActivity(intent);
                    finish(); // Ensure this activity is closed
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
        Log.d("ACTIVITY LIFECYCLE", "onDestroy Called");
        authManager.shutdown(); // Clean up executor service
    }

    public String readJsonFromRaw(Context context, int rawResourceId) {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(rawResourceId);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "Landscape Mode", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "Portrait Mode", Toast.LENGTH_SHORT).show();
        }
    }

}
