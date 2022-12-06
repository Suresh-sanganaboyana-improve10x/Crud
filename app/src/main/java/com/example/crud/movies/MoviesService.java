package com.example.crud.movies;

import com.example.crud.movies.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MoviesService {
    @GET("movies")
    Call<List<Movie>> fetchMovies();

    @POST("movies")
    Call<Movie> addMovie(@Body Movie movie);
}
