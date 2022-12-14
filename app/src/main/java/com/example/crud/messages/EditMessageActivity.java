package com.example.crud.messages;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMessageActivity extends BaseAddEditMessageActivity {
    //TODO : call here Message class to private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneNumberTxt.getText().toString();
            String message = messageTxt.getText().toString();
            updateMessage(this.message.id, name, phoneNumber, message);
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
        //TODO : give class name Message create constructor
        message = new Message();
        message.nameText = name;
        message.phoneNumberText = phoneNumber;
        message.messageText = messageText;

        Call<Void> call = crudService.editMessage(id, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully message updated");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to message update");
            }
        });
    }
}
