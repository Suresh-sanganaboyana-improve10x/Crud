package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie implements Serializable {
    @SerializedName("_id")
    public String id;
    public String seriesId;
    public String movieId;
    public String name;
    public String imageUrl;
    public String description;

    public Movie() {
    }

    public Movie(String seriesId, String movieId, String title, String imageUrl, String description) {
        this.seriesId = seriesId;
        this.movieId = movieId;
        this.name = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}

