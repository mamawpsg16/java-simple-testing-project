package com.example.project.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project.database.AppDatabase;
import com.example.project.database.entities.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    //The LiveData<List<User>> means that the data updates automatically when there’s a change in the database.
    private LiveData<List<User>> userList; //

    public UserViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        userList = db.userDao().getAllUsers(); // Observe users from DB
    }

    public LiveData<List<User>> getAllUsers() {
        return userList;
    }
}
