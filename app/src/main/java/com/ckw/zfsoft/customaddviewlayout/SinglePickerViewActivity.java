package com.ckw.zfsoft.customaddviewlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SinglePickerViewActivity extends AppCompatActivity {

    private Button mShow;
    private TextView mTextShow;
    private SinglePickerView mSinglePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_picker_view);
        mShow = findViewById(R.id.btn_show);
        mTextShow = findViewById(R.id.tv_show);

        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSinglePickerView.show();
            }
        });

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add("item"+i);
        }
        mSinglePickerView = new SinglePickerView(this, new SinglePickerView.ResultHandler() {
            @Override
            public void handle(String selectStr) {
                mTextShow.setText(selectStr);
            }
        },list);
    }
}
