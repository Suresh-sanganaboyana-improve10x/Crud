package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String seriesId = binding.seriesIdTxt.getText().toString();
            String title = binding.seriesNameTxt.getText().toString();
            String imageUrl = binding.seriesUrlTxt.getText().toString();
            addSeries(seriesId, title, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addSeries(String seriesId, String title, String imageUrl) {
        SeriesItem seriesItem = new SeriesItem();
        seriesItem.seriesId = seriesId;
        seriesItem.title = title;
        seriesItem.imageUrl = imageUrl;

        Call<SeriesItem> call = crudService.createSeriesItem(seriesItem);
        call.enqueue(new Callback<SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                showToast("Successfully added the series");
                finish();
            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                showToast("Failed to add series");
            }
        });
    }
}
