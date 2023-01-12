package com.example.crud.movies;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityBaseAddEditMovieBinding;
import com.example.crud.series.SeriesItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected ActivityBaseAddEditMovieBinding binding;
    protected Spinner seriesItemsSp;
    protected CustomSeriesItemsAdapter customSeriesItemsAdapter;
    private ArrayList<SeriesItem> seriesItems = new ArrayList<>();
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseAddEditMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fetchSeries();
        setupSeriesItemsSp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_add_edit_movie_menu, menu);
        return true;
    }

    private void fetchSeries() {
        Call<List<SeriesItem>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                showToast("Successfully fetch seriesList");
                List<SeriesItem> seriesItems = response.body();
                customSeriesItemsAdapter.addAll(seriesItems);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {
                showToast("Failed to fetch seriesList");
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

    private void setupSeriesItemsSp() {
        customSeriesItemsAdapter = new CustomSeriesItemsAdapter(this, android.R.layout.simple_list_item_1, seriesItems);
        binding.seriesItemsSp.setAdapter(customSeriesItemsAdapter);
    }
}