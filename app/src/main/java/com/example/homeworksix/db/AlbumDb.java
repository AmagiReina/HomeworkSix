package com.example.homeworksix.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.homeworksix.dao.AlbumDao;
import com.example.homeworksix.entity.Album;

@Database(entities = {Album.class}, version = 1)
public abstract class AlbumDb extends RoomDatabase {
    public abstract AlbumDao getAlbumDao();
}
