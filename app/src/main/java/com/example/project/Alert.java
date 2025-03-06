package com.example.project;

import android.app.AlertDialog;
import android.content.Context;

public class Alert {
    public static void set(Context context, String title, String message){
        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
