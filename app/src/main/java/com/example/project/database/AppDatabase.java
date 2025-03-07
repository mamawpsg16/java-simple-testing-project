package com.example.project.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project.database.dao.OrderDao;
import com.example.project.database.dao.UserDao;
import com.example.project.database.entities.Order;
import com.example.project.database.entities.User;

@Database(entities = {User.class, Order.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract OrderDao orderDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class, "my_database"
                            )
                            .addMigrations(MIGRATION_1_2) // ✅ Keep migration
                            .build(); // ❌ Removed fallbackToDestructiveMigration()
                }
            }
        }
        return INSTANCE;
    }

    // ✅ Correct Migration
    static final Migration MIGRATION_1_2 = new Migration(1, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN email_address TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN profile_picture TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN is_active INTEGER NOT NULL DEFAULT 1");
        }
    };
}
