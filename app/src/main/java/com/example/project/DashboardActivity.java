package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project.auth.SessionManager;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project.UserAdapter;
import com.example.project.viewmodel.UserViewModel;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private UserAdapter userAdapter;
    private UserViewModel userViewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sessionManager = new SessionManager(this);

        // Redirect if not logged in
        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

//      RecyclerView: Displays the list of items
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//      Adapter: Connects your data (list of users) to the RecyclerView by creating views and binding data to them.
        userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        // observe the LiveData<List<User>> from UserViewModel
        // When the user list changes, it automatically updates the RecyclerView.
        userViewModel.getAllUsers().observe(this, users -> userAdapter.setUsers(users));
    }

    /**
     * Logout function
     */
    public void handleLogout(View view) {
        sessionManager.logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
