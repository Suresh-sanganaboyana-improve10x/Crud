package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesActivity extends BaseAddEditSeriesActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit series");
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // rename id name "done"
        if (item.getItemId() == R.id.add_edit) {
            String seriesId = seriesIdTxt.getText().toString();
            String title = seriesNameTxt.getText().toString();
            String imageUrl = seriesUrlTxt.getText().toString();
            updateSeries(series.id, seriesId, title, imageUrl);
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

    private void updateSeries(String id, String seriesId, String title, String imageUrl) {
        series = new Series();
        series.seriesId = seriesId;
        series.title = title;
        series.imageUrl = imageUrl;

        Call<Void> call = crudService.editSeries(id, series);
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
