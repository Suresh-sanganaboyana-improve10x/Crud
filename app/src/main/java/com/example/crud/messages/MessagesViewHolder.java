package com.example.crud.messages;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class MessagesViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView phoneNumberTxt;
    TextView messageTxt;
    ImageButton deleteImgBtn;

    public MessagesViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        phoneNumberTxt = itemView.findViewById(R.id.phone_number_txt);
        messageTxt = itemView.findViewById(R.id.message_txt);
        deleteImgBtn = itemView.findViewById(R.id.delete_img_btn);
    }
}
