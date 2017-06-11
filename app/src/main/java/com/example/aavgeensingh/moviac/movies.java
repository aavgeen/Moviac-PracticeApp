package com.example.aavgeensingh.moviac;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aavgeen Singh on 3/30/2017.
 */

public class movies implements Serializable{
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("adult")
    boolean isAdult;
    String overview;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("genre_ids")
    int[] genreIDs;
    int id;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("original_language")
    String originalLanguage;
    String title;
    @SerializedName("backdrop_path")
    String backdropPath;
    Double popularity;
    @SerializedName("vote_count")
    int voteCount;
    @SerializedName("vote_average")
    float voteAverage;

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int[] getGenreIDs() {
        return genreIDs;
    }

    public void setGenreIDs(int[] genreIDs) {
        this.genreIDs = genreIDs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }


    public movies(String posterPath, boolean isAdult, String overview, String releaseDate,
                 int[] genreIDs, int id, String originalTitle, String originalLanguage, String title,
                 String backdropPath, Double popularity, int voteCount, float voteAverage) {
        this.posterPath = posterPath;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIDs = genreIDs;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        //13 properties
    }

}