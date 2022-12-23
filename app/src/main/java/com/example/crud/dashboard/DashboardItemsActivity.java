package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.databinding.ActivityDashboardItemsBinding;

import java.util.ArrayList;

public class DashboardItemsActivity extends AppCompatActivity {

    private ActivityDashboardItemsBinding binding;
    private ArrayList<DashboardItem> dashboardItems;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardItemsRv();
    }

    private void setupDashboardItemsRv() {
        binding.dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
        binding.dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();
        DashboardItem message = new DashboardItem("Message", "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024");
        dashboardItems.add(message);

        DashboardItem templates = new DashboardItem("Templates", "https://mixkit.imgix.net/static/home/free-video-templates/free-final-cut-pro-templates.png?q=80&auto=format%2Ccompress");
        dashboardItems.add(templates);

        DashboardItem series = new DashboardItem("Series", "https://www.sleepspa.in/wp-content/uploads/2020/04/money-heist-netflix.jpeg");
        dashboardItems.add(series);

        DashboardItem movies = new DashboardItem("Movies", "https://collegerealitycheck.com/wp-content/uploads/film-studies-degree-374366191-1024x768.jpg");
        dashboardItems.add(movies);
    }
}