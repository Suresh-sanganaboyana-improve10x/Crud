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

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivitySeriesItemsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends BaseActivity {

    private ActivitySeriesItemsBinding binding;
    private ArrayList<SeriesItem> seriesItems = new ArrayList<>();
    private SeriesItemsAdapter seriesItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeriesItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Series");
        setupSeriesItemsAdapter();
        seriesItemsRv();
    }

    private void updateSeries(SeriesItem seriesItem) {
        Intent intent = new Intent(this, EditSeriesItemActivity.class);
        intent.putExtra(Constants.KEY_SERIES_ITEM, seriesItem);
        startActivity(intent);
    }

    private void deleteSeries(String id) {
        Call<Void> call = crudService.deleteSeriesItem(id);
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
        getMenuInflater().inflate(R.menu.series_items_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add){
            Intent intent = new Intent(this, AddSeriesItemActivity.class);
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
        Call<List<SeriesItem>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                hideVisibility();
                List<SeriesItem> seriesItems = response.body();
                seriesItemsAdapter.setData(seriesItems);
                showToast("Successfully fetch the data");
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {
                hideVisibility();
                showToast("Failed to load data");
            }
        });
    }

    private void setupSeriesItemsAdapter() {
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsAdapter.setData(seriesItems);
        seriesItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteSeries(id);
                showToast("Successfully delete series");
            }

            @Override
            public void onEdit(SeriesItem seriesItem) {
                updateSeries(seriesItem);
                showToast("Failed to load data");
            }
        });
    }

    private void seriesItemsRv() {
        binding.seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        binding.seriesItemsRv.setAdapter(seriesItemsAdapter);
    }

    private void showVisibility() {
         binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisibility() {
        binding.progressBar.setVisibility(View.GONE);
    }
}