package com.example.crud.movies;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected Spinner seriesSp;
    protected CustomSeriesAdapter customSeriesAdapter;
    private ArrayList<Series> seriesList = new ArrayList<>();
    protected EditText movieIdTextTxt;
    protected EditText movieNameTextTxt;
    protected EditText imageUrlTextTxt;
    protected EditText descriptionTextTxt;
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        fetchSeries();
        initViews();
        setupSeriesSp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_add_edit_menu, menu);
        return true;
    }

    private void fetchSeries() {
        Call<List<Series>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                showToast("Successfully fetch seriesList");
                List<Series> seriesList1 = response.body();
                customSeriesAdapter.addAll(seriesList1);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                showToast("Failed to fetch seriesList");
            }
        });
    }

    protected void showData() {
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