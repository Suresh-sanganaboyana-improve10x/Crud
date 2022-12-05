package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {
    public ArrayList<Messages> messagesArrayList = new ArrayList<>();
    public RecyclerView messagesRv;
    public MessagesAdapter messagesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");

        setupRecyclerViewForMessages();
    }

    public void editMessage(Messages messages) {
        Intent intent = new Intent(this, AddEditMessageActivity.class);
        intent.putExtra("Messages", messages);
        startActivity(intent);
    }

    public void setOnDeleteMessage(String id) {
        MessageApi messageApi = new MessageApi();
        MessageService messageService = messageApi.createMessageService();
        Call<Void> call = messageService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MessagesActivity.this, "Successfully delete message", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddEditMessageActivity.class);
            startActivity(intent);
            return true;
        } else {
            return  super.onOptionsItemSelected(item);
        }
    }

    public void fetchData() {
        showVisible();
        MessageApi messageApi = new MessageApi();
        MessageService messageService = messageApi.createMessageService();
        Call<List<Messages>> call = messageService.fetchMessages();
        call.enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                hideVisible();
                List<Messages> messagesList = response.body();
                messagesAdapter.setupData(messagesList);
                Toast.makeText(MessagesActivity.this, "Successfully fetch the data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {
                hideVisible();

            }
        });
    }

    public void setupRecyclerViewForMessages() {
        progressBar = findViewById(R.id.progress_bar);
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setupData(messagesArrayList);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                setOnDeleteMessage(id);
                fetchData();
            }

            @Override
            public void onEdit(Messages messages) {
                editMessage(messages);
            }
        });
        messagesRv.setAdapter(messagesAdapter);
    }

    public void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideVisible() {
        progressBar.setVisibility(View.GONE);
    }
}