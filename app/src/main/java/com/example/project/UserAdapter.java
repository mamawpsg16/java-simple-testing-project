package com.example.project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;  // Import the Button class
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.database.entities.User;
import com.example.project.user.EditViewActivity;

import java.util.List;
import android.widget.Toast;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Constants for identifying view types
    private static final int VIEW_TYPE_HEADER = 0;  // For header view
    private static final int VIEW_TYPE_ITEM = 1;    // For user item views

    private List<User> userList;  // List of users to display

    // Method to set users in the adapter
    public void setUsers(List<User> users) {
        this.userList = users;
        notifyDataSetChanged();  // Notify RecyclerView to refresh the data
    }

    // Returns the type of view at a specific position (either header or item)
    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;  // First position is header
    }

    // Creates the view holder based on view type (header or item)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            // Inflate the header layout for the first item
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_header, parent, false);
            return new HeaderViewHolder(view);  // Return header view holder
        } else {
            // Inflate the regular item layout for user data
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            return new UserViewHolder(view);  // Return user item view holder
        }
    }

    // Binds the data to the view holder at a specific position
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            // Casting the holder to UserViewHolder to access its views
            UserViewHolder userHolder = (UserViewHolder) holder;

            // Get the user data at position (adjust for the header row)
            User user = userList.get(position - 1);  // Position - 1 to skip header

            // Set the username and email address from the User object to the TextViews
            userHolder.usernameTextView.setText(user.getUsername());
            userHolder.emailAddressTextView.setText(user.getEmailAddress());

            // Set up the Edit button click listener
            userHolder.btnEdit.setOnClickListener(v -> {
                // Display a Toast message when the Edit button is clicked
//                Toast.makeText(v.getContext(), "Edit " + user.getUsername(), Toast.LENGTH_SHORT).show();

                // v.getContext() gives you the context of the view (the Button)
                // This context is used to start the new activity
                Intent intent = new Intent(v.getContext(), EditViewActivity.class);

                // Pass the user data as extras in the intent
                intent.putExtra("username", user.getUsername());  // Pass the username
                intent.putExtra("email", user.getEmailAddress());  // Pass the email

                // Start EditActivity
                v.getContext().startActivity(intent);
            });

            // Set up the Delete button click listener
            userHolder.btnDelete.setOnClickListener(v -> {
                // Display a Toast message when the Delete button is clicked
                Toast.makeText(v.getContext(), "Delete " + user.getUsername(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    // Returns the total number of items (including the header)
    @Override
    public int getItemCount() {
        return (userList != null) ? userList.size() + 1 : 1;  // Add 1 for the header
    }

    // ViewHolder for user items (each user has a username, email, and buttons)
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, emailAddressTextView;
        Button btnEdit, btnDelete;  // Buttons for editing and deleting user

        public UserViewHolder(View itemView) {
            super(itemView);
            // Initialize the TextViews and Buttons from the layout
            usernameTextView = itemView.findViewById(R.id.textUsername);
            emailAddressTextView = itemView.findViewById(R.id.textEmailAddress);
            btnEdit = itemView.findViewById(R.id.btnEdit);  // Make sure these IDs exist in XML
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    // ViewHolder for the header (no data is set here, just the layout)
    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);  // This is empty for now
        }
    }
}
