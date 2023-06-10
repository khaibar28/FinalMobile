package com.example.h071211020_finalmobile.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTvHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tv_database";
    private static final int DATABASE_VERSION = 1;

    public DatabaseTvHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + DatabaseTvContract.TvEntry.TABLE_NAME + " ("
                + DatabaseTvContract.TvEntry.COLUMN_ID + " INTEGER PRIMARY KEY,"
                + DatabaseTvContract.TvEntry.COLUMN_BACKDROP_PATH + " TEXT,"
                + DatabaseTvContract.TvEntry.COLUMN_ORIGINAL_NAME + " TEXT,"
                + DatabaseTvContract.TvEntry.COLUMN_FIRST_AIR_DATE + " TEXT,"
                + DatabaseTvContract.TvEntry.COLUMN_OVERVIEW + " TEXT,"
                + DatabaseTvContract.TvEntry.COLUMN_POSTER_PATH + " TEXT,"
                + DatabaseTvContract.TvEntry.COLUMN_VOTE_AVERAGE + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTvContract.TvEntry.TABLE_NAME);
        onCreate(db);
    }
}
