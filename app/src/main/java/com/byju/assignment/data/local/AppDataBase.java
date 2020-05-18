package com.byju.assignment.data.local;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.byju.assignment.R;

/*
@Database(entities = {}, version = 1, exportSchema = false)*/
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;

    public abstract RoomDao roomDao();


    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDataBase.class,
                    context.getString(R.string.app_name))
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}


