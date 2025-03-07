package com.example.project.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class EditViewActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        // Initialize views
//        editUsername = findViewById(R.id.editUsername);
//        editEmail = findViewById(R.id.editEmail);
//        btnSave = findViewById(R.id.btnSave);

        // Initialize UserDao
//        userDao = UserDatabase.getInstance(this).userDao();

        // Retrieve the user data passed from the adapter
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");  // Retrieve username
        String email = intent.getStringExtra("email");  // Retrieve email
        Log.d("Edit User", "username = "+ username);
        Log.d("Edit User", "email = " + email );

    }
}
