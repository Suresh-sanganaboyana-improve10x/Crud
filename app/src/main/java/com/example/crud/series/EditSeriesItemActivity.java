package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesItemActivity extends BaseAddEditSeriesActivity {

    private SeriesItem seriesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit series");
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            seriesItem = (SeriesItem) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String seriesId = seriesIdTxt.getText().toString();
            String title = seriesNameTxt.getText().toString();
            String imageUrl = seriesUrlTxt.getText().toString();
            updateSeriesItem(seriesItem.id, seriesId, title, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        seriesIdTxt.setText(seriesItem.seriesId);
        seriesNameTxt.setText(seriesItem.title);
        seriesUrlTxt.setText(seriesItem.imageUrl);
    }

    private void updateSeriesItem(String id, String seriesId, String title, String imageUrl) {
        //TODO : create Constructor for Series
        seriesItem = new SeriesItem();
        seriesItem.seriesId = seriesId;
        seriesItem.title = title;
        seriesItem.imageUrl = imageUrl;

        Call<Void> call = crudService.editSeriesItem(id, seriesItem);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully edited series");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed to edit series");
            }
        });
    }
}
