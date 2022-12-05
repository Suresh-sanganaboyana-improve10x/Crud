package com.example.crud.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {
    public ArrayList<Movie> movieList;
    public RecyclerView moviesRv;
    public MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        setupDataForMovies();
        setRecyclerView();
    }

    public void setRecyclerView() {
        moviesRv = findViewById(R.id.movies_rv);
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void setupDataForMovies() {
        movieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.description = "Dhurga";
        movie.movieId = "1";
        movie.imageUrl = "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024";
        movie.name = "harry";
        movie.seriesId = "2";
        movieList.add(movie);
    }
}