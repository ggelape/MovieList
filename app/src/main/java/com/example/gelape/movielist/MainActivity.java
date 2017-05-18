package com.example.gelape.movielist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;
import com.example.gelape.movielist.adapter.MoviesAdapter;
import com.example.gelape.movielist.model.Movie;
import com.example.gelape.movielist.model.MovieResponse;
import com.example.gelape.movielist.rest.ApiClient;
import com.example.gelape.movielist.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "e1c08c9ea4fe1c84e6eefc727128b6b5";
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    ListView listView;
    Response<MovieResponse> response;
    Intent intent;
    MoviesAdapter moviesAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchItem.collapseActionView();
                MainActivity.this.setTitle(query);
                Call<MovieResponse> call = apiService.getMovieSearch(API_KEY, query);
                call.enqueue(new Callback<MovieResponse>()
                {
                    @Override
                    public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response)
                    {
                        int statusCode = response.code();
                        List<Movie> movies = response.body().getResults();
                        //listView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                        MoviesAdapter adapter = new MoviesAdapter(getApplicationContext(), R.layout.list_item_movie, movies);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<MovieResponse>call, Throwable t)
                    {
                        // Log error here since request failed
                        Log.e(TAG, t.toString());
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (API_KEY.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        listView = (ListView) findViewById(R.id.listView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>()
        {
            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response)
            {
                int statusCode = response.code();
                final List<Movie> movies = response.body().getResults();
                //recyclerView.setAdapter(moviesAdapter = new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                MoviesAdapter adapter = new MoviesAdapter(getApplicationContext(), R.layout.list_item_movie, movies);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t)
            {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
