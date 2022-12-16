package com.example.crud.series;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

public class BaseAddEditSeriesActivity extends BaseActivity {

    protected EditText seriesIdTextTxt;
    protected EditText seriesNameTextTxt;
    protected EditText seriesUrlTextTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        initView();
    }

    protected void initView() {
        seriesIdTextTxt = findViewById(R.id.series_id_text_txt);
        seriesNameTextTxt = findViewById(R.id.series_name_text_txt);
        seriesUrlTextTxt = findViewById(R.id.series_url_text_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }
}