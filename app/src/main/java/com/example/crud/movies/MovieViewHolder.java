package com.example.crud.movies;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.MoviesItemBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    MoviesItemBinding binding;
    public MovieViewHolder(MoviesItemBinding moviesItemBinding) {
        super(moviesItemBinding.getRoot());
        binding = moviesItemBinding;
    }
}
