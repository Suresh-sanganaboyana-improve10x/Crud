package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseAddEditSeriesActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String seriesId = seriesIdTxt.getText().toString();
            String title = seriesNameTxt.getText().toString();
            String imageUrl = seriesUrlTxt.getText().toString();
            addSeries(seriesId, title, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addSeries(String seriesId, String title, String imageUrl) {
        Series series = new Series();
        series.seriesId = seriesId;
        series.title = title;
        series.imageUrl = imageUrl;

        Call<Series> call = crudService.createSeriesItem(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                showToast("Successfully added the series");
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showToast("Failed to add series");
            }
        });
    }
}
