package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Api.CrudApi;
import com.example.crud.Api.CrudService;
import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMessageActivity extends AppCompatActivity {
    private Message message;
    private EditText nameTxt;
    private EditText phoneNumberTxt;
    private EditText messageTxt;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        initViews();
        setupApiService();
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
        } else {
            getSupportActionBar().setTitle("Add Message");
        }
    }

    public void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit) {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneNumberTxt.getText().toString();
            String message = messageTxt.getText().toString();
            if (this.message == null) {
                addMessage(name, phoneNumber, message);
            } else {
                updateMessage(this.message.id, name, phoneNumber, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        nameTxt.setText(message.nameText);
        phoneNumberTxt.setText(message.phoneNumberText);
        messageTxt.setText(message.messageText);
    }

    private void updateMessage(String id, String name, String phoneNumber, String messageText) {
        message = new Message();
        message.nameText = name;
        message.phoneNumberText = phoneNumber;
        message.messageText = messageText;

        setupApiService();
        Call<Void> call = crudService.editMessage(id, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully message updated");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void addMessage(String name, String phoneNumber, String messageTxt) {
        message = new Message();
        message.nameText = name;
        message.phoneNumberText = phoneNumber;
        message.messageText = messageTxt;

        setupApiService();
        Call<Message> call = crudService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("Successfully added the message");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showToast("Failed to add message");
            }
        });
    }

    private void initViews() {
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        messageTxt = findViewById(R.id.message_txt);
    }

    private void showToast(String message) {
        Toast.makeText(AddEditMessageActivity.this, message, Toast.LENGTH_SHORT).show();

    }
}