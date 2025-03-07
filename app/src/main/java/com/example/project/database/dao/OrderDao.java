package com.example.project.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.project.database.entities.Order;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insert(Order order);

    @Query("SELECT * FROM orders WHERE user_id = :userId")
    List<Order> getOrdersByUserId(int userId);
}
