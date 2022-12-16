package com.example.crud.movies;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;
import com.example.crud.series.SeriesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends BaseAddEditMovieActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add movie");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            SeriesItem seriesItem = (SeriesItem) seriesSp.getSelectedItem();
            String seriesId = seriesItem.seriesId;
            String movieId = movieIdTextTxt.getText().toString();
            String title = movieNameTextTxt.getText().toString();
            String imageUrl = imageUrlTextTxt.getText().toString();
            String description = descriptionTextTxt.getText().toString();
            addMovie(seriesId, movieId, title, imageUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(String seriesId, String movieId, String title, String imageUrl, String description) {
        Movie movie = new Movie(seriesId, movieId, title, imageUrl, description);

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
}
