package com.example.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesViewHolder> {
    public List<Messages> messagesList;
    public OnItemActionListener onItemActionListener;
    public void setupData(List<Messages> messages) {
        messagesList = messages;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener itemActionListener) {
        onItemActionListener = itemActionListener;
    }
    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_item, parent, false);
        MessagesViewHolder messagesViewHolder = new MessagesViewHolder(view);
        return messagesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        Messages messages = messagesList.get(position);
        holder.nameTxt.setText(messages.nameText);
        holder.phoneNumberTxt.setText(messages.phoneNumberText);
        holder.messageTxt.setText(messages.messageText);
        holder.deleteImgBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(messages.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(messages);
        });
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
