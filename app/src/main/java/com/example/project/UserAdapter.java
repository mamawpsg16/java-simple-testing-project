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

    private List<User> userList;
    private OnUserClickListener listener;  // Interface instance

    // Constructor to receive the listener
    public UserAdapter(OnUserClickListener listener) {
        this.listener = listener;
    }

    public void setUsers(List<User> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            return new UserViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            UserViewHolder userHolder = (UserViewHolder) holder;
            User user = userList.get(position - 1);

            userHolder.usernameTextView.setText(user.getUsername());
            userHolder.emailAddressTextView.setText(user.getEmailAddress());

            // Use interface callbacks instead of direct click listeners
            userHolder.btnEdit.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEditClick(user);
                }
            });

            userHolder.btnDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDeleteClick(user);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (userList != null) ? userList.size() + 1 : 1;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, emailAddressTextView;
        Button btnEdit, btnDelete;

        public UserViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.textUsername);
            emailAddressTextView = itemView.findViewById(R.id.textEmailAddress);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}