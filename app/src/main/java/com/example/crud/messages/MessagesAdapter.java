package com.example.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messages;
    private OnItemActionListener onItemActionListener;

     void setData(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }
    //TODO : give default method for setOnItemActionListener use this key word
    public void setOnItemActionListener(OnItemActionListener itemActionListener) {
        onItemActionListener = itemActionListener;
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_item, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.nameTxt.setText(message.nameText);
        holder.phoneNumberTxt.setText(message.phoneNumberText);
        holder.messageTxt.setText(message.messageText);
        holder.deleteImgBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(message.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(message);
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
