package com.example.gelape.movielist.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SqliteDb extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "movies";

    // Films table name
    static final String TABLE_FILMS = "films";

    public static final String KEY_TITLE = "Title";
    public static final String KEY_RATING = "Rating";
    public static final String KEY_RELEASEDATE = "ReleaseDate";
    public static final String KEY_OVERVIEW = "Overview";
    public static final String ID = "_id";

    public SqliteDb (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(DATABASE_NAME, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_FILMS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FILMS +"( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TITLE + " TEXT NOT NULL UNIQUE, " +
                KEY_RATING + " TEXT, " +
                KEY_RELEASEDATE + " TEXT, " +
                KEY_OVERVIEW + " TEXT" +
                ");";
        db.execSQL(CREATE_FILMS_TABLE);
        Log.d(TABLE_FILMS, "created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMS);
        onCreate(db);
    }
}
