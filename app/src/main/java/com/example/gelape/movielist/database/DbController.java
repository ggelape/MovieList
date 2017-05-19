package com.example.gelape.movielist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gelape.movielist.MovieDetailsActivity;
import com.example.gelape.movielist.adapter.MoviesAdapter;

public class DbController
{
    private SQLiteDatabase db;
    private SqliteDb bank;

    String success = "Film saved.";
    String failed = "The movie already exists in the database.";

    public DbController(Context context)
    {
        bank = new SqliteDb(context);
    }

    public String insertData(String movieTitleS ,String dataS , String movieDescriptionS , String ratingI)
    {
        ContentValues values;
        long result;

        db = bank.getWritableDatabase();
        values = new ContentValues();
        //  Insert values
        values.put(SqliteDb.KEY_TITLE, movieTitleS);
        values.put(SqliteDb.KEY_RELEASEDATE, dataS);
        values.put(SqliteDb.KEY_OVERVIEW, movieDescriptionS);
        values.put(SqliteDb.KEY_RATING, ratingI);

        result = db.insert(SqliteDb.TABLE_FILMS, null, values);
        db.close();

        if (result ==-1)
            return failed;
        else
            return success;
    }

    public Cursor loadData()
    {
        Cursor cursor;
        String[] fields =  {bank.ID,bank.KEY_TITLE};
        db = bank.getReadableDatabase();
        cursor = db.query(bank.TABLE_FILMS, fields, null, null, null, null, bank.ID + " ASC", null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
