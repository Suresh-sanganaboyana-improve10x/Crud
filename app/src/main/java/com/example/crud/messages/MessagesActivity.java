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

import com.example.crud.Api.CrudApi;
import com.example.crud.Api.CrudService;
import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {
    // rename the object Name to messageList
    private ArrayList<Message> messagesArrayList = new ArrayList<>();
    private RecyclerView messagesRv;
    private MessagesAdapter messagesAdapter;
    private ProgressBar progressBar;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        log("onCreate");
        getSupportActionBar().setTitle("Messages");
        setupRecyclerViewForMessages();
        setupApiService();
    }

    public void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void editMessage(Message messages) {
        Intent intent = new Intent(this, EditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, messages);
        startActivity(intent);
    }

    private void setOnDeleteMessage(String id) {
        Call<Void> call = crudService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Deleted the message");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete message");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
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
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
            return true;
        } else {
            return  super.onOptionsItemSelected(item);
        }
    }

    private void fetchData() {
        showVisible();
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                hideVisible();
                List<Message> messagesList = response.body();
                messagesAdapter.setupData(messagesList);
                showToast("Successfully fetch the data");
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                hideVisible();
                showToast("Failed fetch data");
            }
        });
    }
    // method name change to setupMessagesRv
    private void setupRecyclerViewForMessages() {
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
            public void onEdit(Message message) {
                editMessage(message);
            }
        });
        messagesRv.setAdapter(messagesAdapter);
    }

    private void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisible() {
        progressBar.setVisibility(View.GONE);
    }
}