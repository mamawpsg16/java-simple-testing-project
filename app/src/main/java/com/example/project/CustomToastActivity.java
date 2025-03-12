package com.example.project;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toast);

        // Get reference to the button in XML layout
        Button button = findViewById(R.id.button);

        // Checking on the button
        Button buttonV1 = findViewById(R.id.showToast);

        // Clicked the Button
        buttonV1.setOnClickListener(v -> showMessage());

        // Set an OnClickListener for the button
        button.setOnClickListener(v -> {
            // Get reference to the EditText
            EditText editText = findViewById(R.id.editText);

            // Get text from EditText and show it in a Toast message
            String message = editText.getText().toString();
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            // Hide the keyboard after displaying the Toast
            hideKeyboard();
        });
    }

    // Function to hide the keyboard
    private void hideKeyboard() {
        // Get the currently focused view
        View view = getCurrentFocus();
        if (view != null) {
            // Get InputMethodManager to handle keyboard input
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            // Hide the soft keyboard
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showMessage() {

        // Inflate custom layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                findViewById(R.id.custom_toast_container));

        // Get the TextView from the custom layout
        TextView text = layout.findViewById(R.id.text);
        text.setTextAppearance(R.style.toastTextStyle);

        // Set the text
        text.setText("GeeksForGeeks");

        // Create and show the Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        // Toast Showed
        toast.show();
    }
}
