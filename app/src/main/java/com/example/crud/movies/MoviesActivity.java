package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.movie_add_item) {
            Intent intent = new Intent(this, AddEditMovieActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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
}