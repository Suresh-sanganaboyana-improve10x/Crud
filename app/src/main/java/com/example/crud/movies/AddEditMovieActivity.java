package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud.Api.CrudApi;
import com.example.crud.Api.CrudService;
import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMovieActivity extends BaseActivity {

    private Spinner seriesSp;
    private CustomSeriesAdapter customSeriesAdapter;
    private ArrayList<Series> seriesList = new ArrayList<>();
    private EditText movieIdTextTxt;
    private EditText movieNameTextTxt;
    private EditText imageUrlTextTxt;
    private EditText descriptionTextTxt;
    private Movie movie;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        initViews();
        setupSeriesSp();
        fetchSeries();
        setupApiService();
        if (getIntent().hasExtra(Constants.KEY_MOVIE)) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) getIntent().getSerializableExtra(Constants.KEY_MOVIE);
            showData();
        } else {
            getSupportActionBar().setTitle("Add movie");
        }
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_add_edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.movie_add_edit_btn) {
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

        setupApiService();
        Call<Void> call = crudService.editMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("updated the movie");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update");

            }
        });
    }

    private void fetchSeries() {
        setupApiService();
        Call<List<Series>> call = crudService.fetchSeries();
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

        setupApiService();
        Call<Movie> call = crudService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                showToast("successfully added movie");
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showToast("Failed to add movie");
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