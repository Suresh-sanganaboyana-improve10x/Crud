package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.series.Series;
import com.example.crud.series.SeriesApi;
import com.example.crud.series.SeriesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMovieActivity extends AppCompatActivity {

    private Spinner seriesSp;
    private CustomSeriesAdapter customSeriesAdapter;
    private ArrayList<Series> seriesList = new ArrayList<>();
    private EditText movieIdTextTxt;
    private EditText movieNameTextTxt;
    private EditText imageUrlTextTxt;
    private EditText descriptionTextTxt;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        initViews();
        setupSeriesSp();
        fetchSeries();
        if (getIntent().hasExtra(Constants.KEY_MOVIE)) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) getIntent().getSerializableExtra(Constants.KEY_MOVIE);
            showData();
        } else {
            getSupportActionBar().setTitle("Add movie");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_add_edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.movie_add_edit_btn) {
            Toast.makeText(this, "Cliked", Toast.LENGTH_SHORT).show();
            String movieId = movieIdTextTxt.getText().toString();
            Series series = (Series) seriesSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imageUrl = imageUrlTextTxt.getText().toString();
            String movieName = movieNameTextTxt.getText().toString();
            String description = descriptionTextTxt.getText().toString();
            if (movie == null) {
                addMovie(movieId, seriesId, imageUrl, movieName, description);
            } else {
                updateMovie(movie.id, movieId, seriesId, imageUrl, movieName, description);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateMovie(String id, String movieId, String seriesId, String imageUrl, String title, String description) {
        movie = new Movie();
        movie.movieId = movieId;
        movie.seriesId = seriesId;
        movie.imageUrl = imageUrl;
        movie.name = title;
        movie.description = description;

        MovieApi movieApi = new MovieApi();
        MoviesService moviesService = movieApi.setMovieService();
        Call<Void> call = moviesService.editMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditMovieActivity.this, "updated the movie", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void fetchSeries() {
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchData();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                Toast.makeText(AddEditMovieActivity.this, "fetch series ", Toast.LENGTH_SHORT).show();
                List<Series> seriesList1 = response.body();
                customSeriesAdapter.addAll(seriesList1);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {

            }
        });
    }

    private void addMovie(String movieId, String seriesId, String imageUrl, String title, String description) {
        movie = new Movie();
        movie.movieId = movieId;
        movie.seriesId = seriesId;
        movie.imageUrl = imageUrl;
        movie.name = title;
        movie.description = description;

        MovieApi movieApi = new MovieApi();
        MoviesService moviesService = movieApi.setMovieService();
        Call<Movie> call = moviesService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Toast.makeText(AddEditMovieActivity.this, "successfully added movie", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "failed to add movie", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showData() {
        movieIdTextTxt.setText(movie.movieId);
        movieNameTextTxt.setText(movie.name);
        imageUrlTextTxt.setText(movie.imageUrl);
        descriptionTextTxt.setText(movie.description);
        for (int i = 0; i<customSeriesAdapter.getCount(); i++) {
            Series series = customSeriesAdapter.getItem(i);
            if (movie.id.equals(series.id)) {
                seriesSp.setSelection(i);
            }
        }
    }

    private void setupSeriesSp() {
        seriesSp = findViewById(R.id.series_sp);
        customSeriesAdapter = new CustomSeriesAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesAdapter);
    }

    private void initViews() {
        movieIdTextTxt = findViewById(R.id.movie_id_text_txt);
        movieNameTextTxt = findViewById(R.id.movie_name_text_txt);
        seriesSp = findViewById(R.id.series_sp);
        imageUrlTextTxt = findViewById(R.id.image_url_text_txt);
        descriptionTextTxt = findViewById(R.id.description_text_txt);
    }
}