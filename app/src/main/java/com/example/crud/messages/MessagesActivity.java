package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityMessagesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {

    private ActivityMessagesBinding binding;
    private ArrayList<Message> messages = new ArrayList<>();
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Messages");
        setupMessagesAdapter();
        setupMessagesRv();
    }

    private void editMessage(Message messages) {
        Intent intent = new Intent(this, EditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, messages);
        startActivity(intent);
    }

    private void deleteMessage(String id) {
        showVisible();
        Call<Void> call = crudService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideVisible();
                showToast("Deleted the message");
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideVisible();
                showToast("Failed to delete message");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
            return true;
        } else {
            return  super.onOptionsItemSelected(item);
        }
    }

    private void fetchMessages() {
        showVisible();
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                hideVisible();
                List<Message> messagesList = response.body();
                messagesAdapter.setData(messagesList);
                showToast("Successfully fetch the data");
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                hideVisible();
                showToast("Failed fetch data");
            }
        });
    }

    private void setupMessagesAdapter() {
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messages);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMessage(id);
                fetchMessages();
            }

            @Override
            public void onEdit(Message message) {
                editMessage(message);
            }
        });
    }

    private void setupMessagesRv() {
        binding.messagesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.messagesRv.setAdapter(messagesAdapter);
    }

    private void showVisible() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisible() {
        binding.progressBar.setVisibility(View.GONE);
    }
}