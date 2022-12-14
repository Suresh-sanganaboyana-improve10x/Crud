package com.example.crud.templates;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseAddEditTemplateActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Template");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String message = messageTxt.getText().toString();
            addTemplate(message);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addTemplate(String messageText) {
        //TODO : create constructor for Template
        Template template = new Template();
        template.messageText = messageText;

        Call<Template> call = crudService.addTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                showToast("Successfully added the template");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                //TODO : call show 'toast for Failed to add the Template'
            }
        });
    }
}
