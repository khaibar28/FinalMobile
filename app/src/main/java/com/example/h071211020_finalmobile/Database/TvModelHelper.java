package com.example.h071211020_finalmobile.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.h071211020_finalmobile.Model.TvModel;
import com.example.h071211020_finalmobile.Database.DatabaseTvContract.TvEntry;

import java.util.ArrayList;
import java.util.List;

public class TvModelHelper {

    private SQLiteDatabase database;

    public TvModelHelper(SQLiteDatabase database) {
        this.database = database;
    }

    public long insertTv(TvModel tvModel) {
        ContentValues values = new ContentValues();
        values.put(TvEntry.COLUMN_ID, tvModel.getId());
        values.put(TvEntry.COLUMN_BACKDROP_PATH, tvModel.getBackdrop_path());
        values.put(TvEntry.COLUMN_ORIGINAL_NAME, tvModel.getOriginal_name());
        values.put(TvEntry.COLUMN_FIRST_AIR_DATE, tvModel.getFirst_air_date());
        values.put(TvEntry.COLUMN_OVERVIEW, tvModel.getOverview());
        values.put(TvEntry.COLUMN_POSTER_PATH, tvModel.getPoster_path());
        values.put(TvEntry.COLUMN_VOTE_AVERAGE, tvModel.getVote_average());

        return database.insert(TvEntry.TABLE_NAME, null, values);
    }

    public List<TvModel> getAllTvs() {
        List<TvModel> tvList = new ArrayList<>();

        Cursor cursor = database.query(
                TvEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            TvModel tvModel = new TvModel();
            tvModel.setId(cursor.getInt(cursor.getColumnIndex(TvEntry.COLUMN_ID)));
            tvModel.setBackdrop_path(cursor.getString(cursor.getColumnIndex(TvEntry.COLUMN_BACKDROP_PATH)));
            tvModel.setOriginal_name(cursor.getString(cursor.getColumnIndex(TvEntry.COLUMN_ORIGINAL_NAME)));
            tvModel.setFirst_air_date(cursor.getString(cursor.getColumnIndex(TvEntry.COLUMN_FIRST_AIR_DATE)));
            tvModel.setOverview(cursor.getString(cursor.getColumnIndex(TvEntry.COLUMN_OVERVIEW)));
            tvModel.setPoster_path(cursor.getString(cursor.getColumnIndex(TvEntry.COLUMN_POSTER_PATH)));
            tvModel.setVote_average(cursor.getString(cursor.getColumnIndex(TvEntry.COLUMN_VOTE_AVERAGE)));

            tvList.add(tvModel);
        }

        cursor.close();
        return tvList;
    }

    public boolean isTvExists(int tvId) {
        Cursor cursor = database.query(
                TvEntry.TABLE_NAME,
                null,
                TvEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(tvId)},
                null,
                null,
                null
        );

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public int deleteTv(int tvId) {
        return database.delete(
                TvEntry.TABLE_NAME,
                TvEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(tvId)}
        );
    }
}
