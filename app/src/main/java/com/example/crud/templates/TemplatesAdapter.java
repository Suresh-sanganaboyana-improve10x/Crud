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
    private List<Template> templateList;
    //TODO : give default method
    public void setData(List<Template> templates) {
        templateList = templates;
        notifyDataSetChanged();
    }
    public void setOnItemActionListener(OnItemActionListener itemActionListener) {
        onItemActionListener = itemActionListener;
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
        Template template = templateList.get(position);
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
        return templateList.size();
    }
}
