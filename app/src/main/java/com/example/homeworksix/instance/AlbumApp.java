package com.example.homeworksix.instance;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.homeworksix.db.AlbumDb;

@SuppressLint("Registered")
public class AlbumApp extends Application {

    private static final String DB_NAME = "albumDB";
    private static AlbumApp instance;
    private AlbumDb database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AlbumDb.class,
                DB_NAME).allowMainThreadQueries().build();
    }

    public static AlbumApp getInstance() {
        return instance;
    }

    public AlbumDb getDatabase() {
        return database;
    }
}
