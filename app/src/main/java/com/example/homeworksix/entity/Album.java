package com.example.homeworksix.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.example.homeworksix.converters.DateConvert;
import java.io.Serializable;
import java.sql.Date;

@Entity
@TypeConverters(DateConvert.class)
public class Album extends DBEntity implements Serializable {

    @PrimaryKey
    private Long id;
    private String title;
    private String leadSinger;
    private Date date;
    private String pathImage;

    public Album(Long id, String title, String leadSinger, Date date) {
        this.id = id;
        this.title = title;
        this.leadSinger = leadSinger;
        this.date = date;
    }

    @Ignore
    public Album(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeadSinger() {
        return leadSinger;
    }

    public void setLeadSinger(String leadSinger) {
        this.leadSinger = leadSinger;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
