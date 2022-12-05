package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class DashboardViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView titleTxt;
    LinearLayout dashboardLayout;

    public DashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_view);
        titleTxt = itemView.findViewById(R.id.title_txt);
        dashboardLayout = itemView.findViewById(R.id.dashboard_layout);
    }
}
