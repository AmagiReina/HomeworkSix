package com.example.homeworksix.repository;

import android.os.AsyncTask;
import com.example.homeworksix.dao.AlbumDao;
import com.example.homeworksix.db.DbCallback;
import com.example.homeworksix.entity.Album;
import java.util.List;

public class SelectAlbumBySingerRepository extends AsyncTask<String, Void, Object> {
    private AlbumDao dao;
    private DbCallback callback;

    public SelectAlbumBySingerRepository(AlbumDao dao, DbCallback callback) {
        this.dao = dao;
        this.callback = callback;
    }

    @Override
    protected Object doInBackground(String... singers) {
        if (singers != null && singers.length > 0) {
            return dao.getAlbumBySinger(singers[0]);
        } else {
            return dao.getAllAlbums();
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (o instanceof List) {
            callback.onSelectCollection((List<Album>) o);
        } else {
            callback.onSelectSingleItem((Album) o);
        }
    }
}
