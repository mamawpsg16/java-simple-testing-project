package com.example.project.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "orders",
        foreignKeys = @ForeignKey(
                entity = User.class,          // References the User table
                parentColumns = "id",         // The primary key in the User table
                childColumns = "user_id",     // The column in this table
                onDelete = ForeignKey.CASCADE // Deletes orders if the user is deleted
        )
)
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_id", index = true)  // ✅ Now, this is a foreign key!
    public int userId;

    @ColumnInfo(name = "order_details")
    public String orderDetails;

    public Order(int userId, String orderDetails) {
        this.userId = userId;
        this.orderDetails = orderDetails;
    }
}
