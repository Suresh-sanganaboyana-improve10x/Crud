package com.example.crud.templates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTemplateActivity extends AppCompatActivity {
    EditText messageTxt;
    Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        initView();
        if (getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
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
            if (this.template == null) {
                addTemplate(message);
            } else {
                updateTemplate(this.template.id, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void showData() {
        messageTxt.setText(template.messageText);
    }

    public void initView() {
        messageTxt = findViewById(R.id.message_txt);
    }

    public void updateTemplate(String id, String messageTxt) {
        template = new Template();
        template.messageText = messageTxt;

        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplatesService();
        Call<Void> call = templatesService.editTemplate(id, template);
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
        template = new Template();
        template.messageText = messageText;

        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplatesService();
        Call<Template> call = templatesService.addTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully added the template", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {

            }
        });
    }
}