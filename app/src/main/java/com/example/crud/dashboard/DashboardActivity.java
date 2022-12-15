package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ArrayList<DashboardItem> dashboardItems;
    private RecyclerView dashboardItemsRv;
    //TODO : object name change dashboardItemsAdapter;
    private DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardRv();
    }
    //TODO : change method name setupDashboardItemsRv
    private void setupDashboardRv() {
        //TODO : create method for findViews to store there findViews
        dashboardItemsRv = findViewById(R.id.dashboard_rv);
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setupData(dashboardItems);
        dashboardItemsRv.setAdapter(dashboardAdapter);
    }

    private void setupData() {
        hideProgressBar();
        //TODO : create constructor for this
        dashboardItems = new ArrayList<>();
        DashboardItem message = new DashboardItem();
        message.imageUrl = "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024";
        message.title = "Message";
        dashboardItems.add(message);

        DashboardItem templates = new DashboardItem();
        templates.imageUrl = "https://mixkit.imgix.net/static/home/free-video-templates/free-final-cut-pro-templates.png?q=80&auto=format%2Ccompress";
        templates.title = "Templates";
        dashboardItems.add(templates);

        DashboardItem series = new DashboardItem();
        series.imageUrl = "https://www.sleepspa.in/wp-content/uploads/2020/04/money-heist-netflix.jpeg";
        series.title = "Series";
        dashboardItems.add(series);

        DashboardItem movies = new DashboardItem();
        movies.imageUrl = "https://collegerealitycheck.com/wp-content/uploads/film-studies-degree-374366191-1024x768.jpg";
        movies.title = "Movies";
        dashboardItems.add(movies);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}