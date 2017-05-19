package com.example.gelape.movielist;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gelape.movielist.database.DbController;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle bundle = getIntent().getExtras();
        String posterUrl = bundle.getString("posterUrl2");
        final String movieTitleS = bundle.getString("movieTitleS");
        final String dataS = bundle.getString("dataS");
        final String movieDescriptionS = bundle.getString("movieDescriptionS");
        final String ratingI = bundle.getString("ratingI");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle(movieTitleS);

        TextView movTitle = (TextView) findViewById(R.id.movTitle);
        TextView releaseDate = (TextView) findViewById(R.id.releaseDate);
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView overview = (TextView) findViewById(R.id.overview);

        Picasso.with(this).cancelRequest((ImageView) findViewById(R.id.imageView));
        Picasso.with(this).load(posterUrl).into( (ImageView) findViewById(R.id.imageView));
        movTitle.setText(movieTitleS);
        releaseDate.setText(dataS);
        rating.setText(ratingI);
        overview.setText(movieDescriptionS);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DbController crud = new DbController(getBaseContext());
                String result;
                result = crud.insertData(movieTitleS, ratingI, dataS, movieDescriptionS);
                Snackbar.make(view, "Filme salvo com sucesso! ", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });
    }
}
