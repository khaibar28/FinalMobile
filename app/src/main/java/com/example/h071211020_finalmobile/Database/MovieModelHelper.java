package com.example.h071211020_finalmobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.h071211020_finalmobile.Model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MovieModelHelper {
    private DatabaseMovieHelper dbHelper;
    private SQLiteDatabase database;

    public MovieModelHelper(Context context) {
        dbHelper = new DatabaseMovieHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertMovie(MovieModel movie) {
        ContentValues values = MappingMovieHelper.toContentValues(movie);
        return database.insert(DatabaseMovieContract.MovieEntry.TABLE_NAME, null, values);
    }

    public int updateMovie(MovieModel movie) {
        ContentValues values = MappingMovieHelper.toContentValues(movie);
        String selection = DatabaseMovieContract.MovieEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(movie.getId())};
        return database.update(DatabaseMovieContract.MovieEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public int deleteMovie(int movieId) {
        String selection = DatabaseMovieContract.MovieEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(movieId)};
        return database.delete(DatabaseMovieContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
    }

    public List<MovieModel> getAllMovies() {
        List<MovieModel> movies = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseMovieContract.MovieEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MovieModel movie = MappingMovieHelper.fromCursor(cursor);
                movies.add(movie);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return movies;
    }

    public MovieModel getMovieById(int movieId) {
        String selection = DatabaseMovieContract.MovieEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(movieId)};
        Cursor cursor = database.query(
                DatabaseMovieContract.MovieEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            MovieModel movie = MappingMovieHelper.fromCursor(cursor);
            cursor.close();
            return movie;
        }
        return null;
    }
}
