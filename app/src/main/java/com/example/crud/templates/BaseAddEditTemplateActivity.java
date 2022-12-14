package com.example.crud.templates;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.crud.Api.CrudApi;
import com.example.crud.Api.CrudService;
import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected EditText messageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_messages_menu, menu);
        return true;
    }

    private void initView() {
        messageTxt = findViewById(R.id.message_txt);
    }
}