package com.byju.assignment.data.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.byju.assignment.R;
import com.byju.assignment.model.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
@TypeConverters(SourceConverter.class)
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


