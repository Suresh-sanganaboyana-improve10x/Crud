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

import com.example.crud.Api.CrudApi;
import com.example.crud.Api.CrudService;
import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {

    private ArrayList<Movie> movieList = new ArrayList<>();
    private RecyclerView moviesRv;
    private MoviesAdapter moviesAdapter;
    private CrudService crudService;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        setupApiService();
        log("onCreate");
        getSupportActionBar().setTitle("Movies");
        setRecyclerView();
    }

    private void showVisibility() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisibility() {
        progressBar.setVisibility(View.GONE);
    }

    public void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // id name change ("add)
        if (item.getItemId() == R.id.movie_add_item) {
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
    //change method name ("setupMoviesRv")
    private void setRecyclerView() {
        progressBar = findViewById(R.id.progress_bar);
        moviesRv = findViewById(R.id.movies_rv);
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movieList);
        moviesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMovie(id);
                showToast("Successfully deleted a movie");
            }

            @Override
            public void onEdit(Movie movie) {
                setEditMovie(movie);
                showToast("Failed to load data");
            }
        });
        moviesRv.setAdapter(moviesAdapter);
    }

    private void deleteMovie(String id) {
        Call<Void> call = crudService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("delete movie successfully");
                fetchMovies();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed to delete movie");
            }
        });
    }
    // method name change "editMovie"
    private void setEditMovie(Movie movie) {
        Intent intent = new Intent(this, EditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIE, movie);
        startActivity(intent);
    }
}