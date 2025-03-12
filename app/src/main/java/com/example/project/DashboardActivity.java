package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project.auth.SessionManager;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project.database.AppDatabase;
import com.example.project.database.dao.UserDao;
import com.example.project.database.entities.User;
import com.example.project.user.EditViewActivity;
import com.example.project.viewmodel.UserViewModel;
import androidx.appcompat.app.AlertDialog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DashboardActivity extends AppCompatActivity implements OnUserClickListener, View.OnClickListener {
    private UserAdapter userAdapter;
    private UserViewModel userViewModel;
    private SessionManager sessionManager;
    AppDatabase db;
    UserDao userDao;
    String loggedInUsername;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // getting the bundle back from the android
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString("key1", "Default"); // Use default value directly
        } else {
            title = "Default";
        }

        Log.d("DASHBOARD", title);

        Button btnTest = findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);
        sessionManager = new SessionManager(this);
        db = AppDatabase.getInstance(this);
        userDao = db.userDao();

        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // 🔹 Get the logged-in user (Modify based on how you store user data)
        // Create a single-threaded executor (store it as a field if needed)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Background task: Fetch user from Room Database
            User user = userDao.getUserByUsername(sessionManager.getLoggedInUsername());

            // Switch to the Main Thread for UI updates
            runOnUiThread(() -> {
                if (user != null) {
                    loggedInUsername = user.getUsername();
                }

                if ("admin".equals(loggedInUsername)) {
                    recyclerView.setVisibility(View.VISIBLE);
                    userViewModel.getAllUsers().observe(DashboardActivity.this, users -> userAdapter.setUsers(users));
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            });

            // Shutdown the executor after the task is complete
            executor.shutdown();
        });

    }

    @Override
    public void onEditClick(User user) {
        Intent intent = new Intent(this, EditViewActivity.class);
        intent.putExtra("username", user.getUsername());
        intent.putExtra("email", user.getEmailAddress());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(User user) {
        AppDatabase db = AppDatabase.getInstance(this);
        UserDao userDao = db.userDao();

        new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete " + user.getUsername() + "?")
                .setPositiveButton("Delete", (dialog, which) ->
                        Executors.newSingleThreadExecutor().execute(() -> {
                            userDao.delete(user);
                            runOnUiThread(() -> showToast(user.getUsername() + " deleted successfully!"));
                        })
                ).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())  // Dismiss properly
                .show();
    }

    public void handleLogout(View view) {
        sessionManager.logout();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, BundleTestActivity.class);
        startActivity(intent);
    }
}
