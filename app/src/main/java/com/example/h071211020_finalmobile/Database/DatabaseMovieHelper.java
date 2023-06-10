package com.example.h071211020_finalmobile.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseMovieHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseMovieContract.MovieEntry.TABLE_NAME + " (" +
                    DatabaseMovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseMovieContract.MovieEntry.COLUMN_ID + " INTEGER," +
                    DatabaseMovieContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT," +
                    DatabaseMovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT," +
                    DatabaseMovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT," +
                    DatabaseMovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT," +
                    DatabaseMovieContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT," +
                    DatabaseMovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseMovieContract.MovieEntry.TABLE_NAME;

    public DatabaseMovieHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
