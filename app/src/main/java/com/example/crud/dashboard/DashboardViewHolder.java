package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
        //TODO : change dashboardViewHolder to dashboardItemViewHolder
public class DashboardViewHolder extends RecyclerView.ViewHolder {
    //TODO ; imageImg to dashboardImg
    ImageView imageImg;
    TextView titleTxt;

    public DashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        imageImg = itemView.findViewById(R.id.image_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
