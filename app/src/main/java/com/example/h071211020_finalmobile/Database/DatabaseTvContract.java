package com.example.h071211020_finalmobile.Database;

import android.provider.BaseColumns;

public class DatabaseTvContract {
    private DatabaseTvContract() {}

    public static class TvEntry implements BaseColumns {
        public static final String TABLE_NAME = "tv";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_ORIGINAL_NAME = "original_name";
        public static final String COLUMN_FIRST_AIR_DATE = "first_air_date";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    }
}
