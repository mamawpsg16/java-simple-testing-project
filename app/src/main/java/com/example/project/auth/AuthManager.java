package com.example.project.auth;

import android.content.Context;
import com.example.project.database.AppDatabase;
import com.example.project.database.dao.UserDao;
import com.example.project.database.entities.User;
import org.mindrot.jbcrypt.BCrypt;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AuthManager {
    private final UserDao userDao;
    private final ExecutorService executorService;
    private final SessionManager sessionManager;

    public AuthManager(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.userDao = db.userDao();
        this.executorService = Executors.newSingleThreadExecutor();
        this.sessionManager = new SessionManager(context); // Initialize SessionManager
    }

    /**
     * Validates the user credentials asynchronously and saves session state
     */
    public void login(String username, String password, AuthCallback callback) {
        executorService.execute(() -> {
            User user = userDao.getUserByUsername(username);
            if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
                callback.onFailure("Invalid username or password.");
            } else {
                sessionManager.setLoggedIn(true); // Save login session
                callback.onSuccess(user);
            }
        });
    }

    /**
     * Logs out the user and clears session state
     */
    public void logout() {
        sessionManager.logout(); // Clear session
    }

    /**
     * Clears resources when authentication is no longer needed
     */
    public void shutdown() {
        executorService.shutdown();
    }

    /**
     * Callback interface for authentication results
     */
    public interface AuthCallback {
        void onSuccess(User user);
        void onFailure(String errorMessage);
    }
}
