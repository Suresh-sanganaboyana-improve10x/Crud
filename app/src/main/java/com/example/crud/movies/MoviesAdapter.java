package com.example.crud.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.MoviesItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    private OnItemActionListener onItemActionListener;

     void setData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

     void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesItemBinding binding = MoviesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(binding);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.binding.setMovie(movie);
//        Picasso.get().load(movie.imageUrl).into(holder.binding.movieImageUrl);
//        holder.binding.titleTextTxt.setText(movie.name);
        holder.binding.deleteImgBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(movie.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            onItemActionListener.onEdit(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
