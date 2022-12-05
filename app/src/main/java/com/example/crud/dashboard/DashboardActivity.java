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
    public ArrayList<Dashboard> dashboards;
    public RecyclerView dashboardRv;
    public DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupDataForDashboard();
        setupRecyclerViewForDashboard();
    }

    public void setupRecyclerViewForDashboard() {
        dashboardRv = findViewById(R.id.dashboard_rv);
        dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setupData(dashboards);
        dashboardRv.setAdapter(dashboardAdapter);
    }

    public void setupDataForDashboard() {
        dashboards = new ArrayList<>();
        Dashboard dashboard1 = new Dashboard();
        dashboard1.imageUrl = "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024";
        dashboard1.titleText = "Message";
        dashboards.add(dashboard1);

        Dashboard dashboard2 = new Dashboard();
        dashboard2.imageUrl = "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024";
        dashboard2.titleText = "Templates";
        dashboards.add(dashboard2);

        Dashboard dashboard3 = new Dashboard();
        dashboard3.imageUrl = "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024";
        dashboard3.titleText = "Series";
        dashboards.add(dashboard3);

        Dashboard dashboard4 = new Dashboard();
        dashboard4.imageUrl = "https://www.techadvisor.com/wp-content/uploads/2022/06/how-to-read-facebook-messages-without-them-knowing-main.jpg?quality=50&strip=all&w=1024";
        dashboard4.titleText = "Movies";
        dashboards.add(dashboard4);
    }
}