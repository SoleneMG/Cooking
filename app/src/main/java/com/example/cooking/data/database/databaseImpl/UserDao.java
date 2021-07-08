package com.example.cooking.data.database.databaseImpl;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.cooking.model.User;

import java.util.List;

@androidx.room.Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("DELETE FROM user")
    void deleteAll();
}
