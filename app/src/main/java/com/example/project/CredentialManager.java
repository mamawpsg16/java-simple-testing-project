package com.example.project;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import java.util.HashMap;

public class CredentialManager {
    private static final String PREF_NAME = "UserCredentials";
    private static final String KEY_CREDENTIALS = "credentials";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public CredentialManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    // Save credentials (username -> password)
    public void saveCredentials(String username, String password) {
        // Get existing credentials
        HashMap<String, String> credentials = getCredentials();

        // Add new credential
        credentials.put(username, password);

        // Convert HashMap to JSON string
        String jsonString = gson.toJson(credentials);

        // Save to SharedPreferences
        editor.putString(KEY_CREDENTIALS, jsonString);
        editor.apply();
    }

    // Retrieve all credentials
    public HashMap<String, String> getCredentials() {
        String jsonString = sharedPreferences.getString(KEY_CREDENTIALS, "{}"); // Default is empty JSON
        return gson.fromJson(jsonString, HashMap.class); // Convert JSON to HashMap
    }

    // Get password for a specific username
    public String getPassword(String username) {
        HashMap<String, String> credentials = getCredentials();
        return credentials.getOrDefault(username, null);
    }

    // Check if username and password match
    public Boolean checkCredentials(String username, String password) {
        HashMap<String, String> users = getCredentials();
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }


    // Clear all credentials
    public void clearCredentials() {
        editor.remove(KEY_CREDENTIALS);
        editor.apply();
    }
}
