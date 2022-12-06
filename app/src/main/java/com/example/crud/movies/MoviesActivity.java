package com.example.crud.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {
    public ArrayList<Movie> movieList = new ArrayList<>();
    public RecyclerView moviesRv;
    public MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
//        setupDataForMovies();
        setRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setGetMovie();
    }

    public void setGetMovie() {
        MovieApi movieApi = new MovieApi();
        MoviesService moviesService = movieApi.setMovieService();
        Call<List<Movie>> call = moviesService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movieList = response.body();
                moviesAdapter.setData(movieList);
                Toast.makeText(MoviesActivity.this, "Successfully fetch movies", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
    }

    public void setRecyclerView() {
        moviesRv = findViewById(R.id.movies_rv);
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movieList);
        moviesRv.setAdapter(moviesAdapter);
    }

//    public void setupDataForMovies() {
//        movieList = new ArrayList<>();
//        Movie movie = new Movie();
//        movie.description = "Dhurga";
//        movie.movieId = "1";
//        movie.imageUrl = "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024";
//        movie.name = "harry";
//        movie.seriesId = "2";
//        movieList.add(movie);
//    }
}