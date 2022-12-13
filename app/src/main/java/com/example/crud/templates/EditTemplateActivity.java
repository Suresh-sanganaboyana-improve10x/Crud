package com.example.crud.templates;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTemplateActivity extends BaseAddEditTemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // rename id name "done"
        if (item.getItemId() == R.id.edit) {
            String message = messageTxt.getText().toString();
            updateTemplate(this.template.id, message);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        messageTxt.setText(template.messageText);
    }

    private void updateTemplate(String id, String messageTxt) {
        template = new Template();
        template.messageText = messageTxt;

        Call<Void> call = crudService.editTemplate(id, template);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated template");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //Toast
            }
        });
    }
}
