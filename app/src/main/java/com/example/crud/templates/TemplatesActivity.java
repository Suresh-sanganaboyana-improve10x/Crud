package com.example.crud.templates;

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
import com.example.crud.databinding.ActivityTemplatesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {

    private ActivityTemplatesBinding binding;
    private ArrayList<Template> templates = new ArrayList<>();
    private TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTemplatesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Templates");
        setupTemplatesAdapter();
        setupTemplatesRv();
    }

    private void editTemplate(Template templates) {
        Intent intent = new Intent(this, EditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, templates);
        startActivity(intent);
    }

    private void deleteTemplate(String id) {
        showVisible();
        Call<Void> call = crudService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideVisible();
                showToast("Successfully delete the template");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideVisible();
                showToast("Failed to delete template");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.templates_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddTemplateActivity.class);
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
        fetchData();
    }

    private void fetchData() {
        showVisible();
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                hideVisible();
                showToast("Successfully fetch the data");
                List<Template> templatesList = response.body();
                templatesAdapter.setData(templatesList);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                hideVisible();
                showToast("Failed to fetch data");
            }
        });
    }

    private void setupTemplatesAdapter() {
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templates);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteTemplate(id);
                fetchData();
            }

            @Override
            public void onEdit(Template templates) {
                editTemplate(templates);
            }
        });
    }

    private void setupTemplatesRv() {
        binding.templatesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.templatesRv.setAdapter(templatesAdapter);
    }

    private void showVisible() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisible() {
        binding.progressBar.setVisibility(View.GONE);
    }
}