package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class SeriesItemViewHolder extends RecyclerView.ViewHolder {

    ImageView imageUrl;
    TextView titleTxt;
    ImageButton deleteImgBtn;

    public SeriesItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageUrl = itemView.findViewById(R.id.image_url);
        titleTxt = itemView.findViewById(R.id.title_txt);
        deleteImgBtn = itemView.findViewById(R.id.delete_img_btn);
    }
}
