package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Api.CrudApi;
import com.example.crud.Api.CrudService;
import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListActivity extends BaseActivity {

    private ArrayList<Series> seriesList = new ArrayList<>();
    // rename the seriesListRv// doubt
    private RecyclerView seriesItemsRv;
    private SeriesAdapter seriesAdapter;
    private ProgressBar progressBar;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        log("onCreate");
        getSupportActionBar().setTitle("Series");
        setupApiService();
        seriesItemsRv();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void updateSeries(Series series) {
        Intent intent = new Intent(this, EditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }

    private void deleteSeries(String id) {
        Call<Void> call = crudService.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Deleted series successfully");
                fetchSeries();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete series");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add){
            Intent intent = new Intent(this, AddSeriesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchSeries();
    }

    private void fetchSeries() {
        showVisibility();
        Call<List<Series>> call = crudService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideVisibility();
                List<Series> seriesList = response.body();
                seriesAdapter.setupData(seriesList);
                showToast("Successfully fetch the data");
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideVisibility();
                //Toast
            }
        });
    }
    // rename method name
    private void seriesItemsRv() {
        progressBar = findViewById(R.id.progress_bar);
        seriesItemsRv = findViewById(R.id.series_items_rv);
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesAdapter.setupData(seriesList);
        seriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteSeries(id);
                showToast("Successfully delete series");
            }

            @Override
            public void onEdit(Series series) {
                updateSeries(series);
                //Toast
            }
        });
        seriesItemsRv.setAdapter(seriesAdapter);
    }

    private void showVisibility() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisibility() {
        progressBar.setVisibility(View.GONE);
    }
}