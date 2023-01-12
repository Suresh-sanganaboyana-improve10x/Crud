package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityMoviesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {

    private ActivityMoviesBinding binding;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Movies");
        setupMoviesAdapter();
        setupMoviesRv();
    }

    private void showVisibility() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisibility() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddMovieActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchMovies();
    }

    private void fetchMovies() {
        showVisibility();
        Call<List<Movie>> call = crudService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                hideVisibility();
                List<Movie> movieList = response.body();
                moviesAdapter.setData(movieList);
                showToast("Successfully fetch movies");
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                hideVisibility();
                showToast("Failed to load data");
            }
        });
    }

    private void setupMoviesAdapter() {
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movies);
        moviesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMovie(id);
                showToast("Successfully deleted a movie");
            }

            @Override
            public void onEdit(Movie movie) {
                editMovie(movie);
                showToast("Failed to load data");
            }
        });
    }

    private void setupMoviesRv() {
        binding.moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.moviesRv.setAdapter(moviesAdapter);
    }

    private void deleteMovie(String id) {
        showVisibility();
        Call<Void> call = crudService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideVisibility();
                showToast("delete movie successfully");
                fetchMovies();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideVisibility();
                showToast("failed to delete movie");
            }
        });
    }

    private void editMovie(Movie movie) {
        Intent intent = new Intent(this, EditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIE, movie);
        startActivity(intent);
    }
}