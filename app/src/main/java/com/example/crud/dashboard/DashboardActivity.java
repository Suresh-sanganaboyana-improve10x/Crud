package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    // dashboards doubt
    private ArrayList<Dashboard> dashboardItems;
    private RecyclerView dashboardRv;
    private DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardRv();
    }

    private void setupDashboardRv() {
        dashboardRv = findViewById(R.id.dashboard_rv);
        dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setupData(dashboardItems);
        dashboardRv.setAdapter(dashboardAdapter);
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();
        Dashboard message = new Dashboard();
        message.imageUrl = "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024";
        message.titleText = "Message";
        dashboardItems.add(message);

        Dashboard templates = new Dashboard();
        templates.imageUrl = "https://mixkit.imgix.net/static/home/free-video-templates/free-final-cut-pro-templates.png?q=80&auto=format%2Ccompress";
        templates.titleText = "Templates";
        dashboardItems.add(templates);

        Dashboard series = new Dashboard();
        series.imageUrl = "https://www.sleepspa.in/wp-content/uploads/2020/04/money-heist-netflix.jpeg";
        series.titleText = "Series";
        dashboardItems.add(series);

        Dashboard movies = new Dashboard();
        movies.imageUrl = "https://collegerealitycheck.com/wp-content/uploads/film-studies-degree-374366191-1024x768.jpg";
        movies.titleText = "Movies";
        dashboardItems.add(movies);
    }
}