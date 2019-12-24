package com.example.homeworksix.repository;

import android.os.AsyncTask;
import com.example.homeworksix.dao.AlbumDao;
import com.example.homeworksix.db.DbCallback;
import com.example.homeworksix.entity.Album;
import java.sql.Date;
import java.util.List;

public class SelectAlbumByDateRepository extends AsyncTask<Date, Void ,Object> {
    private AlbumDao dao;
    private DbCallback callback;

    public SelectAlbumByDateRepository(AlbumDao dao, DbCallback callback) {
        this.dao = dao;
        this.callback = callback;
    }

    @Override
    protected Object doInBackground(Date... dates) {
        if (dates != null && dates.length > 0) {
            return dao.getAlbumByDate(dates[0]);
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
