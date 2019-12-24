package com.example.homeworksix.repository;

import android.os.AsyncTask;
import com.example.homeworksix.dao.AlbumDao;
import com.example.homeworksix.db.DbCallback;
import com.example.homeworksix.entity.Album;
import java.util.List;

public class SelectAlbumByTitleRepository extends AsyncTask<String, Void, Object> {
    private AlbumDao dao;
    private DbCallback callback;

    public SelectAlbumByTitleRepository(AlbumDao dao, DbCallback callback) {
        this.dao = dao;
        this.callback = callback;
    }

    @Override
    protected Object doInBackground(String... titles) {
        if (titles != null && titles.length > 0) {
            return dao.getAlbumByTitle(titles[0]);
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
