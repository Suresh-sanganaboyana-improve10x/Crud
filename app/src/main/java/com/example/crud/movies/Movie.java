package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie implements Serializable {
    @SerializedName("_id")
    public String id;
    public String description;
    public String imageUrl;
    public String movieId;
    public String name;
    public String seriesId;
}

