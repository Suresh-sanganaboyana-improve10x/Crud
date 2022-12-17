package com.example.crud.series;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

public class BaseAddEditSeriesActivity extends BaseActivity {

    protected EditText seriesIdTxt;
    protected EditText seriesNameTxt;
    protected EditText seriesUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_add_edit_series_item);
        initView();
    }

    protected void initView() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesUrlTxt = findViewById(R.id.series_url_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_add_edit_series_items_menu, menu);
        return true;
    }
}