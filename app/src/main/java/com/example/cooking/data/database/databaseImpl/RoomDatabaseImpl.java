package com.example.cooking.data.database.databaseImpl;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.cooking.data.database.CookingDatabase;
import com.example.cooking.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RoomDatabaseImpl extends RoomDatabase implements CookingDatabase {
    private static volatile RoomDatabaseImpl INSTANCE;
    public abstract UserDao userDao();


    public static RoomDatabaseImpl getDatabase (final Context context){
        if(INSTANCE == null){
            synchronized (RoomDatabaseImpl.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDatabaseImpl.class, "cooking_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
