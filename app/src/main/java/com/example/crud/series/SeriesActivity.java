package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesActivity extends AppCompatActivity {
    public ArrayList<Series> seriesList = new ArrayList<>();
    public RecyclerView seriesRv;
    public SeriesAdapter seriesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        setRecyclerView();
    }

    public void editSeriesData(Series series) {
        Intent intent = new Intent(this, AddEditSeriesActivity.class);
        intent.putExtra("Series", series);
        startActivity(intent);
    }

    public void deleteSeries(String id) {
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Void> call = seriesService.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

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
            Intent intent = new Intent(this, AddEditSeriesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchSeriesData();
    }

    public void fetchSeriesData() {
        showVisibility();
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchData();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideVisibility();
                List<Series> seriesList = response.body();
                seriesAdapter.setupData(seriesList);
                Toast.makeText(SeriesActivity.this, "Successfully fetch the data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideVisibility();
            }
        });
    }

    public void setRecyclerView() {
        progressBar = findViewById(R.id.progress_bar);
        seriesRv = findViewById(R.id.series_rv);
        seriesRv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesAdapter.setupData(seriesList);
        seriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteSeries(id);
                fetchSeriesData();
                Toast.makeText(SeriesActivity.this, "Successfully delete series", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEdit(Series series) {
                editSeriesData(series);
            }
        });
        seriesRv.setAdapter(seriesAdapter);
    }

    public void showVisibility() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideVisibility() {
        progressBar.setVisibility(View.GONE);
    }
}