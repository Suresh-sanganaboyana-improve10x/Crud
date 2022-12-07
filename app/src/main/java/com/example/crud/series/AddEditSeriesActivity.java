package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends AppCompatActivity {
    private Series series;
    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText seriesUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        initView();
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit series");
            series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        } else {
            getSupportActionBar().setTitle("Add series");
        }
    }

    private void initView() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesUrlTxt = findViewById(R.id.series_url_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_edit) {
            String seriesId = seriesIdTxt.getText().toString();
            String title = seriesNameTxt.getText().toString();
            String imageUrl = seriesUrlTxt.getText().toString();
            if (this.series == null) {
                addSeries(seriesId, title, imageUrl);
            } else {
                updateSeries(series.id, seriesId, title, imageUrl);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        seriesIdTxt.setText(series.seriesId);
        seriesNameTxt.setText(series.title);
        seriesUrlTxt.setText(series.imageUrl);
    }

    private void updateSeries(String id,String seriesId, String title, String imageUrl) {
        series = new Series();
        series.seriesId = seriesId;
        series.title = title;
        series.imageUrl = imageUrl;

        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Void> call = seriesService.editSeries(id, series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditSeriesActivity.this, "Successfully edited series", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Edit failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addSeries(String seriesId, String title, String imageUrl) {
        series = new Series();
        series.seriesId = seriesId;
        series.title = title;
        series.imageUrl = imageUrl;

        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Series> call = seriesService.addSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                Toast.makeText(AddEditSeriesActivity.this, "Successfully added the series", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {

            }
        });
    }
}