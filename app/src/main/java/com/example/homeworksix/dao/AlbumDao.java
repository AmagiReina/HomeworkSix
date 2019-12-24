package com.example.homeworksix.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;
import com.example.homeworksix.converters.DateConvert;
import com.example.homeworksix.entity.Album;
import java.sql.Date;
import java.util.List;

@Dao
public interface AlbumDao {

    // Выборка по всем альбомам
    @Query("SELECT * FROM album")
    List<Album> getAllAlbums();

    // Выборка по id альбома
    @Query("SELECT * FROM album WHERE id = :id")
    Album getAlbumById(Long id);

    // Выборка по названию альбома (возвращает список)
    @Query("SELECT * FROM album WHERE album.title = :title")
    List<Album> getAlbumByTitle(String title);

    // Выборка по исполнителю (возвращает список)
    @Query("SELECT * FROM album WHERE album.leadSinger = :singer")
    List<Album> getAlbumBySinger(String singer);

    // Выборка по дате выхода альбома
    // (возвращает список, для даты используется конверетер)
    @Query("SELECT * FROM album WHERE album.date = :date")
    @TypeConverters(DateConvert.class)
    List<Album> getAlbumByDate(Date date);

    // Вставка альбома в базу данных
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Album album);

    // Обновление данных о фильме
    @Update
    void update(Album album);

    // Удаление альбома
    @Delete
    void delete(Album album);
}
