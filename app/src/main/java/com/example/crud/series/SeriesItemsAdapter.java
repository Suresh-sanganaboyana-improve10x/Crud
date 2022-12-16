package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesItemsAdapter extends RecyclerView.Adapter<SeriesViewHolder> {

    private List<SeriesItem> seriesItems;
    private OnItemActionListener onItemActionListener;
    //TODO : method name change to setData and method give default and use this key word
    public void setupData(List<SeriesItem> seriesItems) {
        this.seriesItems = seriesItems;
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
        SeriesItem seriesItem = seriesItems.get(position);
        Picasso.get().load(seriesItem.imageUrl).into(holder.imageUrl);
        holder.titleTextTxt.setText(seriesItem.title);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(seriesItem.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(seriesItem);
        });
    }

    @Override
    public int getItemCount() {
        return seriesItems.size();
    }
}
