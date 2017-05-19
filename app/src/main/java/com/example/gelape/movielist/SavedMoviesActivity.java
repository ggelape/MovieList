package com.example.gelape.movielist;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.gelape.movielist.database.DbController;
import com.example.gelape.movielist.database.SqliteDb;

public class SavedMoviesActivity extends AppCompatActivity
{
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movies);
        setTitle("Saved Movies");

        DbController crud = new DbController(getBaseContext());
        Cursor cursor = crud.loadData();

        String[] titles = new String[] {SqliteDb.ID, SqliteDb.KEY_TITLE};
        int[] idViews = new int[] {R.id.idNumber, R.id.idTitle};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.adapter_layout,cursor,titles,idViews, 0);
        list = (ListView)findViewById(R.id.moviesList);
        list.setAdapter(adapter);
    }
}
