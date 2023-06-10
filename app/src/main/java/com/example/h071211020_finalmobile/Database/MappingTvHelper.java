package com.example.h071211020_finalmobile.Database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.h071211020_finalmobile.Database.DatabaseTvContract;
import com.example.h071211020_finalmobile.Model.TvModel;

public class MappingTvHelper {
    public static ContentValues toContentValues(TvModel tv) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTvContract.TvEntry.COLUMN_ID, tv.getId());
        values.put(DatabaseTvContract.TvEntry.COLUMN_BACKDROP_PATH, tv.getBackdrop_path());
        values.put(DatabaseTvContract.TvEntry.COLUMN_ORIGINAL_NAME, tv.getOriginal_name());
        values.put(DatabaseTvContract.TvEntry.COLUMN_FIRST_AIR_DATE, tv.getFirst_air_date());
        values.put(DatabaseTvContract.TvEntry.COLUMN_OVERVIEW, tv.getOverview());
        values.put(DatabaseTvContract.TvEntry.COLUMN_POSTER_PATH, tv.getPoster_path());
        values.put(DatabaseTvContract.TvEntry.COLUMN_VOTE_AVERAGE, tv.getVote_average());
        return values;
    }

    public static TvModel fromCursor(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatabaseTvContract.TvEntry.COLUMN_ID);
        int backdropPathIndex = cursor.getColumnIndex(DatabaseTvContract.TvEntry.COLUMN_BACKDROP_PATH);
        int originalNameIndex = cursor.getColumnIndex(DatabaseTvContract.TvEntry.COLUMN_ORIGINAL_NAME);
        int firstAirDateIndex = cursor.getColumnIndex(DatabaseTvContract.TvEntry.COLUMN_FIRST_AIR_DATE);
        int overviewIndex = cursor.getColumnIndex(DatabaseTvContract.TvEntry.COLUMN_OVERVIEW);
        int posterPathIndex = cursor.getColumnIndex(DatabaseTvContract.TvEntry.COLUMN_POSTER_PATH);
        int voteAverageIndex = cursor.getColumnIndex(DatabaseTvContract.TvEntry.COLUMN_VOTE_AVERAGE);

        int id = cursor.getInt(idIndex);
        String backdropPath = cursor.getString(backdropPathIndex);
        String originalName = cursor.getString(originalNameIndex);
        String firstAirDate = cursor.getString(firstAirDateIndex);
        String overview = cursor.getString(overviewIndex);
        String posterPath = cursor.getString(posterPathIndex);
        String voteAverage = cursor.getString(voteAverageIndex);

        TvModel tv = new TvModel();
        tv.setId(id);
        tv.setBackdrop_path(backdropPath);
        tv.setOriginal_name(originalName);
        tv.setFirst_air_date(firstAirDate);
        return tv;
    }
}
