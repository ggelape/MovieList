package com.example.gelape.movielist.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import com.example.gelape.movielist.R;
import com.example.gelape.movielist.model.Movie;
import com.squareup.picasso.Picasso;


public class MoviesAdapter extends RecyclerView.Adapter <MoviesAdapter.MovieViewHolder>
{
    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout moviesLayout;

        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView moviePoster;

        public MovieViewHolder(View v)
        {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            moviePoster = (ImageView) v.findViewById(R.id.moviePoster);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context)
    {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder (final MovieViewHolder holder, final int position)
    {
        String posterUrl = "http://image.tmdb.org/t/p/w185/"+movies.get(position).getPosterPath();
        Picasso.with(context).cancelRequest(holder.moviePoster);
        Picasso.with(context).load(posterUrl).into(holder.moviePoster);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }
}
