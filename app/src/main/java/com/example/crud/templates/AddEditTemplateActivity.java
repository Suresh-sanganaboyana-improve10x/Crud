package com.example.crud.templates;

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

public class AddEditTemplateActivity extends AppCompatActivity {
    EditText messageTxt;
    Templates templates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        initView();
        if (getIntent().hasExtra("Templates")) {
            getSupportActionBar().setTitle("Edit Template");
            templates = (Templates) getIntent().getSerializableExtra("Templates");
            showData();
        } else {
            getSupportActionBar().setTitle("Add Template");
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
            String message = messageTxt.getText().toString();
            if (this.templates == null) {
                addTemplate(message);
            } else {
                updateTemplates(this.templates.id, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void showData() {
        messageTxt.setText(templates.messageText);
    }

    public void initView() {
        messageTxt = findViewById(R.id.message_txt);
    }

    public void updateTemplates(String id, String messageTxt) {
        templates = new Templates();
        templates.messageText = messageTxt;

        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplatesService();
        Call<Void> call = templatesService.editTemplate(id, templates);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully updated template", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void addTemplate(String messageText) {
        Templates templates = new Templates();
        templates.messageText = messageText;

        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplatesService();
        Call<Templates> call = templatesService.addTemplate(templates);
        call.enqueue(new Callback<Templates>() {
            @Override
            public void onResponse(Call<Templates> call, Response<Templates> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully added the template", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Templates> call, Throwable t) {

            }
        });
    }
}