package com.example.gelape.movielist.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gelape.movielist.MovieDetailsActivity;
import com.example.gelape.movielist.R;
import com.example.gelape.movielist.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesAdapter extends ArrayAdapter <Movie>
{
    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    String posterUrl;
    String posterUrl2;
    String movieTitleS;
    String dataS;
    String movieDescriptionS;
    String ratingI;

    public MoviesAdapter(Context context, int rowLayout, List<Movie> movies)
    {
        super(context,rowLayout,movies);
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return super.getCount();
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {
        //region View Holder Declarations and handling
        View convertView;
        final MovieViewHolder holder;

        if (view == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
            holder = new MovieViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            convertView = view;
            holder = (MovieViewHolder) convertView.getTag();
        }
        //endregion

        posterUrl = "http://image.tmdb.org/t/p/w185/"+movies.get(position).getPosterPath();
        Picasso.with(context).cancelRequest(holder.moviePoster);
        Picasso.with(context).load(posterUrl).into(holder.moviePoster);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                movieTitleS = holder.movieTitle.getText().toString();
                dataS = holder.data.getText().toString();
                movieDescriptionS = holder.movieDescription.getText().toString();
                ratingI = holder.rating.getText().toString();
                posterUrl2 = "http://image.tmdb.org/t/p/w300/"+movies.get(position).getPosterPath();
                Intent intent = new Intent(context.getApplicationContext(), MovieDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("posterUrl2",posterUrl2);
                intent.putExtra("movieTitleS",movieTitleS);
                intent.putExtra("dataS",dataS);
                intent.putExtra("movieDescriptionS",movieDescriptionS);
                intent.putExtra("ratingI",ratingI);
                context.startActivity(intent);

            }
        });

        return convertView;

    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView moviePoster;
        CardView cardView;

        public MovieViewHolder(View v)
        {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            moviePoster = (ImageView) v.findViewById(R.id.moviePoster);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
            cardView = (CardView) v.findViewById(R.id.cardView);
        }


    }

}
