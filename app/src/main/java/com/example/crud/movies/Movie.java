package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("_id")
    public String id;
    public String description;
    public String imageUrl;
    public String movieId;
    public String name;
    public String seriesId;
}
