package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    ImageView imageUrl;
    TextView titleTextTxt;
    ImageButton deleteBtn;

    public SeriesViewHolder(@NonNull View itemView) {
        super(itemView);
        imageUrl = itemView.findViewById(R.id.image_url);
        titleTextTxt = itemView.findViewById(R.id.title_text_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
