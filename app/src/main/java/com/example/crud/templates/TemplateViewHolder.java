package com.example.crud.templates;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.TemplatesItemBinding;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

    TemplatesItemBinding binding;

    public TemplateViewHolder(TemplatesItemBinding templatesItemBinding) {
        super(templatesItemBinding.getRoot());
        binding = templatesItemBinding;
    }
}
