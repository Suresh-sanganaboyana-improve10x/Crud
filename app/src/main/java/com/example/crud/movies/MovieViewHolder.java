package com.example.crud.movies;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView movieImageUrl;
    TextView titleTextTxt;
    ImageButton deleteImgBtn;
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImageUrl = itemView.findViewById(R.id.movie_image_url);
        titleTextTxt = itemView.findViewById(R.id.title_text_txt);
        deleteImgBtn = itemView.findViewById(R.id.delete_img_btn);
    }
}
