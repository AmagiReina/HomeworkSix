package com.example.homeworksix.db;

import com.example.homeworksix.entity.Album;
import com.example.homeworksix.entity.DBEntity;

import java.util.List;

public interface DbCallback<T extends DBEntity> {
    void onSelectCollection(List<Album> collection);
    void onSelectSingleItem(T item);
    void onSave();
    void onUpdate();
    void onDelete();
}
