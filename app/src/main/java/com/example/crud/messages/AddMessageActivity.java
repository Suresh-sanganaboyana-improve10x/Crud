package com.example.crud.messages;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseAddEditMessageActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Message");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String name = nameTextTxt.getText().toString();
            String phoneNumber = phoneNumberTextTxt.getText().toString();
            String message = messageTextTxt.getText().toString();
            addMessage(name, phoneNumber, message);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMessage(String name, String phoneNumber, String messageTxt) {
        Message message = new Message(name, phoneNumber,messageTxt);

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
}
