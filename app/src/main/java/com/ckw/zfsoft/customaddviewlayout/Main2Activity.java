package com.ckw.zfsoft.customaddviewlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ckw.zfsoft.customaddviewlayout.TimePicker.PickerView;
import com.ckw.zfsoft.customaddviewlayout.TimePicker.TimeSelector;

/**
 * 时间选择器的使用
 */
public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mShow;
    private Button mJump;
    private TextView mTvShow;
    TimeSelector timeSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mShow = findViewById(R.id.btn_show);
        mJump = findViewById(R.id.btn_jump);
        mTvShow = findViewById(R.id.tv_show);
        mShow.setOnClickListener(this);

        mJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,SinglePickerViewActivity.class);
                startActivity(intent);
            }
        });
        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
                mTvShow.setText(time);
            }
        }, "2015-11-22 17:34", "2018-1-1 15:20");

    }


    @Override
    public void onClick(View v) {
        timeSelector.show();
    }
}
