package com.example.crud.messages;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

public class BaseAddEditMessageActivity extends BaseActivity {

    protected Message message;
    protected EditText nameTextTxt;
    protected EditText phoneNumberTextTxt;
    protected EditText messageTextTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        initViews();
        //Todo : Delete log method in onCreate, onResume methods all classes
        log("onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_messages_menu, menu);
        return true;
    }

    private void initViews() {
        nameTextTxt = findViewById(R.id.name_text_txt);
        phoneNumberTextTxt = findViewById(R.id.phone_number_text_txt);
        messageTextTxt = findViewById(R.id.message_text_txt);
    }
}