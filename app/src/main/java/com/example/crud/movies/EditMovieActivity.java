package com.example.crud.movies;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.series.SeriesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMovieActivity extends BaseAddEditMovieActivity {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_MOVIE)) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) getIntent().getSerializableExtra(Constants.KEY_MOVIE);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String movieId = binding.movieIdTextTxt.getText().toString();
            SeriesItem seriesItem = (SeriesItem) seriesItemsSp.getSelectedItem();
            String seriesId = seriesItem.seriesId;
            String imageUrl = binding.imageUrlTextTxt.getText().toString();
            String movieName = binding.movieNameTextTxt.getText().toString();
            String description = binding.descriptionTextTxt.getText().toString();
            updateMovie(movie.id, movieId, seriesId, imageUrl, movieName, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateMovie(String id, String movieId, String seriesId, String imageUrl, String title, String description) {
        Movie movie = new Movie();
        movie.movieId = movieId;
        movie.seriesId = seriesId;
        movie.imageUrl = imageUrl;
        movie.name = title;
        movie.description = description;

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

    protected void showData() {
        binding.setMovie(movie);
//        binding.movieIdTextTxt.setText(movie.movieId);
//        binding.movieNameTextTxt.setText(movie.name);
//        binding.imageUrlTextTxt.setText(movie.imageUrl);
//        binding.descriptionTextTxt.setText(movie.description);
        for (int i = 0; i< customSeriesItemsAdapter.getCount(); i++) {
            SeriesItem seriesItem = customSeriesItemsAdapter.getItem(i);
            if (movie.id.equals(seriesItem.id)) {
                seriesItemsSp.setSelection(i);
            }
        }
    }
}