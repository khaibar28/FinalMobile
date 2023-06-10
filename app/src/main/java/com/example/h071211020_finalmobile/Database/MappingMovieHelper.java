package com.example.h071211020_finalmobile.Database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.h071211020_finalmobile.Database.DatabaseMovieContract;
import com.example.h071211020_finalmobile.Model.MovieModel;

public class MappingMovieHelper {
    public static ContentValues toContentValues(MovieModel movie) {
        ContentValues values = new ContentValues();
        values.put(DatabaseMovieContract.MovieEntry.COLUMN_ID, movie.getId());
        values.put(DatabaseMovieContract.MovieEntry.COLUMN_BACKDROP_PATH, movie.getBackdrop_path());
        values.put(DatabaseMovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE, movie.getOriginal_title());
        values.put(DatabaseMovieContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getRelease_date());
        values.put(DatabaseMovieContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        values.put(DatabaseMovieContract.MovieEntry.COLUMN_POSTER_PATH, movie.getPoster_path());
        values.put(DatabaseMovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVote_average());
        return values;
    }

    public static MovieModel fromCursor(Cursor cursor) {
        MovieModel movie = new MovieModel();
        movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMovieContract.MovieEntry.COLUMN_ID)));
        movie.setBackdrop_path(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMovieContract.MovieEntry.COLUMN_BACKDROP_PATH)));
        movie.setOriginal_title(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE)));
        movie.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMovieContract.MovieEntry.COLUMN_RELEASE_DATE)));
        movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMovieContract.MovieEntry.COLUMN_OVERVIEW)));
        movie.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMovieContract.MovieEntry.COLUMN_POSTER_PATH)));
        movie.setVote_average(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMovieContract.MovieEntry.COLUMN_VOTE_AVERAGE)));
        return movie;
    }
}
