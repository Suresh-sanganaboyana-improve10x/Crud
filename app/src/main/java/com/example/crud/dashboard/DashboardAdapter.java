package com.example.crud.dashboard;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.movies.MoviesActivity;
import com.example.crud.R;
import com.example.crud.series.SeriesListActivity;
import com.example.crud.templates.TemplatesActivity;
import com.example.crud.messages.MessagesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
    //TODO : change the DashboardAdapter to DashboardItemsAdapter
public class DashboardAdapter extends RecyclerView.Adapter<DashboardViewHolder> {
    //TODO : change name dashboardList to dashboardItems;
    private ArrayList<DashboardItem> dashboardList;
    //TODO : method name change to setData
    public void setupData(ArrayList<DashboardItem> dashboardItems) {
        dashboardList = dashboardItems;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item, parent, false);
        DashboardViewHolder dashboardViewHolder = new DashboardViewHolder(view);
        return dashboardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        DashboardItem dashboardItem = dashboardList.get(position);
        Picasso.get().load(dashboardItem.imageUrl).into(holder.imageImg);
        holder.titleTxt.setText(dashboardItem.title);
        holder.itemView.setOnClickListener(view -> {
            if (holder.titleTxt.getText().toString().equalsIgnoreCase("Message")) {
                Intent intent = new Intent(holder.itemView.getContext(), MessagesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            //TODO : remove extra lines in this onBindViewHolder
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.itemView.getContext(), TemplatesActivity.class);
                holder.itemView.getContext().startActivity(intent);

            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Series")) {
                Intent intent = new Intent(holder.itemView.getContext(), SeriesListActivity.class);
                holder.itemView.getContext().startActivity(intent);

            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.itemView.getContext(), MoviesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }
}
