package com.example.gelape.movielist.model;
import com.google.gson.annotations.SerializedName;


public class Movie
{
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private Double voteAverage;

    public Movie(String posterPath, String overview,String releaseDate,String originalLanguage,String title,Double voteAverage)
    {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath()
    {
        return posterPath;
    }
    public void setPosterPath()
    {
        this.posterPath = posterPath;
    }
    public String getOverview()
    {
        return overview;
    }
    public void setOverview()
    {
        this.overview = overview;
    }
    public String getReleaseDate()
    {
        return releaseDate;
    }
    public void setReleaseDate()
    {
        this.releaseDate = releaseDate;
    }
    public String getOriginalLanguage()
    {
        return originalLanguage;
    }
    public void setOriginalLanguage()
    {
        this.originalLanguage = originalLanguage;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle()
    {
        this.title = title;
    }
    public Double getVoteAverage()
    {
        return voteAverage;
    }
    public void setVoteAverage()
    {
        this.voteAverage = voteAverage;
    }
}
