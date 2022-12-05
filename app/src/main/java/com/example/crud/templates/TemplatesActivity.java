package com.example.crud.templates;

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

public class TemplatesActivity extends AppCompatActivity {

    public ArrayList<com.example.crud.templates.Templates> templatesArrayList = new ArrayList<>();
    public RecyclerView templatesRv;
    public TemplatesAdapter templatesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setupRecyclerViewForTemplates();
    }

    public void setEditTemplate(Templates templates) {
        Intent intent = new Intent(this, AddEditTemplateActivity.class);
        intent.putExtra("Templates", templates);
        startActivity(intent);
    }

    public void setDeleteTemplate(String id) {
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplatesService();
        Call<Void> call = templatesService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully delete the template", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

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
            Intent intent = new Intent(this, AddEditTemplateActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    public void fetchData() {
        showVisible();
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplatesService();
        Call<List<Templates>> call = templatesService.fetchTemplate();
        call.enqueue(new Callback<List<com.example.crud.templates.Templates>>() {
            @Override
            public void onResponse(Call<List<com.example.crud.templates.Templates>> call, Response<List<com.example.crud.templates.Templates>> response) {
                hideVisible();
                Toast.makeText(TemplatesActivity.this, "Successfully fetch the data", Toast.LENGTH_SHORT).show();
                List<Templates> templatesList = response.body();
                templatesAdapter.setData(templatesList);
            }

            @Override
            public void onFailure(Call<List<com.example.crud.templates.Templates>> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                hideVisible();
            }
        });
    }

    public void setupRecyclerViewForTemplates() {
        progressBar = findViewById(R.id.progress_bar);
        templatesRv = findViewById(R.id.templates_rv);
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templatesArrayList);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                setDeleteTemplate(id);
                fetchData();
            }

            @Override
            public void onEdit(Templates templates) {
                setEditTemplate(templates);
            }
        });
        templatesRv.setAdapter(templatesAdapter);
    }

    public void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideVisible() {
        progressBar.setVisibility(View.GONE);
    }
}