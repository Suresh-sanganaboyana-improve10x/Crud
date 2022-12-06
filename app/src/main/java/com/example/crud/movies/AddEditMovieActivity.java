package com.example.crud.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.series.Series;
import com.example.crud.series.SeriesApi;
import com.example.crud.series.SeriesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMovieActivity extends AppCompatActivity {
    EditText movieIdTextTxt;
    EditText movieNameTextTxt;
    Spinner seriesSp;
    EditText imageUrlTextTxt;
    EditText descriptionTextTxt;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        initViews();
    }

    public void fetchSeries() {
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchData();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                Toast.makeText(AddEditMovieActivity.this, "fetch series ", Toast.LENGTH_SHORT).show();
                List<Series> seriesList = response.body();

            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {

            }
        });
    }

    public void initViews() {
        movieIdTextTxt = findViewById(R.id.movie_id_text_txt);
        movieNameTextTxt = findViewById(R.id.movie_name_text_txt);
        seriesSp = findViewById(R.id.series_sp);
        imageUrlTextTxt = findViewById(R.id.image_url_text_txt);
        descriptionTextTxt = findViewById(R.id.description_text_txt);
    }
}