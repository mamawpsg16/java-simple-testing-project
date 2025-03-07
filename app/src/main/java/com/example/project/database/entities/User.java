package com.example.project.database.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "username")
    public String username;

    @NonNull
    @ColumnInfo(name = "password")
    public String password;

    @NonNull
    @ColumnInfo(name = "email_address") // Add email column
    public String emailAddress;

    @Nullable
    @ColumnInfo(name = "profile_picture")
    private String profilePicture; // This can be NULL

    @ColumnInfo(name = "is_active")
    public boolean isActive; // Boolean column (1 = true, 0 = false)

    public User(String username, String password, @Nullable String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.profilePicture = null; // ✅ Default to null
        this.isActive = true; // ✅ Default to true (1 in SQLite)
    }

    public String getPassword() {
        return password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Integer getId(){
        return id;
    }

    // Setters (optional)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmailAddress(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}