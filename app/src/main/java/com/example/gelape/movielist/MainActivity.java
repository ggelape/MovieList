package com.example.gelape.movielist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>()
        {
            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response)
            {
                List<Movie> movies = response.body().getResults();
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
