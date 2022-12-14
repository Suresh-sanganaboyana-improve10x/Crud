package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;
    //TODO : Adapter class name change to SeriesItemsAdapter
public class SeriesAdapter extends RecyclerView.Adapter<SeriesViewHolder> {

    private List<Series> seriesList;
    private OnItemActionListener onItemActionListener;
    //TODO : method name change to setData and method give default and use this key word
    public void setupData(List<Series> seriesList1) {
        seriesList = seriesList1;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }
    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item, parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(view);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        Series series = seriesList.get(position);
        Picasso.get().load(series.imageUrl).into(holder.imageUrl);
        holder.titleTextTxt.setText(series.title);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(series.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(series);
        });
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}
