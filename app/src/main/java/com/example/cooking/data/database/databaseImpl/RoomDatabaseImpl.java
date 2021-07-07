package com.example.cooking.data.database.databaseImpl;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cooking.model.Ingredient;
import com.example.cooking.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RoomDatabaseImpl extends RoomDatabase {
    private static volatile RoomDatabaseImpl INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract Dao dao();

    public static RoomDatabaseImpl getDatabase(final Context context){
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
