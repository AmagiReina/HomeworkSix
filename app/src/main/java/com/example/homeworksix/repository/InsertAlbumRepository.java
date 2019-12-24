package com.example.homeworksix.repository;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.example.homeworksix.dao.AlbumDao;
import com.example.homeworksix.db.DbCallback;
import com.example.homeworksix.entity.Album;

import java.util.List;

public class InsertAlbumRepository extends AsyncTask<Object, Void, Void> {
    private AlbumDao dao;
    private DbCallback callback;

    public InsertAlbumRepository(AlbumDao dao, DbCallback callback) {
        this.dao = dao;
        this.callback = callback;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected Void doInBackground(Object... objects) {
        if (objects[0] instanceof List) {
            List<Album> albums = (List<Album>) objects[0];
            albums.forEach(a->dao.save(a));
        } else {
            dao.save((Album) objects[0]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.onSave();
    }
}
