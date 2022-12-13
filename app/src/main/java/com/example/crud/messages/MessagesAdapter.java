package com.example.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messageList;
    private OnItemActionListener onItemActionListener;
    // method name change to setData
    public void setupData(List<Message> messages) {
        messageList = messages;
        notifyDataSetChanged();
    }

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
        //change the object name ("message")
        Message messages = messageList.get(position);
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
        return messageList.size();
    }
}
