package com.example.crud.templates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplateViewHolder> {

    private OnItemActionListener onItemActionListener;

    private List<Template> templates;

     void setData(List<Template> templates) {
        this.templates = templates;
        notifyDataSetChanged();
    }

     void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.templates_item, parent, false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(view);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templates.get(position);
        holder.messageTextTxt.setText(template.messageText);
        holder.deleteImgBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(template.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(template);
        });
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }
}
