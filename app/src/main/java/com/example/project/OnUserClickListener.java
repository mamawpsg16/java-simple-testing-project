package com.example.project;

import com.example.project.database.entities.User;

public interface OnUserClickListener {
    void onEditClick(User user);
    void onDeleteClick(User user);
}