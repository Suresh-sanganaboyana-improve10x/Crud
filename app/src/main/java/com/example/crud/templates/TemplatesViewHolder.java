package com.example.crud.templates;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class TemplatesViewHolder extends RecyclerView.ViewHolder {

    TextView messageTextTxt;
    ImageButton deleteImgBtn;

    public TemplatesViewHolder(@NonNull View itemView) {
        super(itemView);
        messageTextTxt = itemView.findViewById(R.id.message_text_txt);
        deleteImgBtn = itemView.findViewById(R.id.delete_img_btn);
    }
}
