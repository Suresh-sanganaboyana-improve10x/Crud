package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMessageActivity extends AppCompatActivity {
    Messages messages;
    EditText nameTxt;
    EditText phoneNumberTxt;
    EditText messageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        initView();
        if (getIntent().hasExtra("Messages")) {
            getSupportActionBar().setTitle("Edit Messages");
            messages = (Messages) getIntent().getSerializableExtra("Messages");
            showData();
        } else {
            getSupportActionBar().setTitle("Add Messages");
        }
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
            if (this.messages == null) {
                addMessage(name, phoneNumber, message);
            } else {
                updateMessages(this.messages.id, name, phoneNumber, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void showData() {
        nameTxt.setText(messages.nameText);
        phoneNumberTxt.setText(messages.phoneNumberText);
        messageTxt.setText(messages.messageText);
    }

    public void updateMessages(String id, String name, String phoneNumber, String messageText) {
        messages = new Messages();
        messages.nameText = name;
        messages.phoneNumberText = phoneNumber;
        messages.messageText = messageText;

        MessageApi messageApi = new MessageApi();
        MessageService messageService = messageApi.createMessageService();
        Call<Void> call = messageService.editMessage(id, messages);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditMessageActivity.this, "Successfully message updated", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void addMessage(String name, String phoneNumber, String messageTxt) {
        messages = new Messages();
        messages.nameText = name;
        messages.phoneNumberText = phoneNumber;
        messages.messageText = messageTxt;

        MessageApi messageApi = new MessageApi();
        MessageService messageService = messageApi.createMessageService();
        Call<Messages> call = messageService.createMessages(messages);
        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                Toast.makeText(AddEditMessageActivity.this, "Successfully added the message", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                Toast.makeText(AddEditMessageActivity.this, "Failed to add message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView() {
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        messageTxt = findViewById(R.id.message_txt);
    }

//    public void handleAddBtn() {
//        addBtn = findViewById(R.id.add_btn);
//        addBtn.setOnClickListener(view -> {
//            nameTxt = findViewById(R.id.name_txt);
//            String name =nameTxt.getText().toString();
//            phoneNumberTxt = findViewById(R.id.phone_number_txt);
//            String phoneNumber = phoneNumberTxt.getText().toString();
//            messageTxt = findViewById(R.id.message_txt);
//            String message = messageTxt.getText().toString();
//            addMessage(name, phoneNumber, message);
//        });
//    }
}