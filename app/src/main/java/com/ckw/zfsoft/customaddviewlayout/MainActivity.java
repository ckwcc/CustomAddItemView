package com.ckw.zfsoft.customaddviewlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 点击按钮添加一行自定义控件的使用
 * 点击add 添加一行，点击 remove all 删除全部item
 * 点击item的左侧头部，删除当前item
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomAddViewLayout mCustomAddViewLayout;
    private Button mAdd;
    private Button mRemoveAll;
    private Button mJump;
    private int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomAddViewLayout = findViewById(R.id.customView);
        mAdd = findViewById(R.id.btn_add);
        mRemoveAll = findViewById(R.id.btn_removeAll);
        mJump = findViewById(R.id.btn_jump);

        mJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RadioButtonActivity.class);
                startActivity(intent);
            }
        });


        mRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomAddViewLayout.deleteAllView();
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View addView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_type_text, null);
                TextView remove = addView.findViewById(R.id.tv_remove);
                remove.setText("item:"+count);
                count++;
                remove.setLayoutParams(new RelativeLayout.LayoutParams(60,100));
                remove.setOnClickListener(MainActivity.this);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
                addView.setLayoutParams(layoutParams);
                mCustomAddViewLayout.addItemView(addView);
            }
        });
    }

    @Override
    public void onClick(View v) {
        mCustomAddViewLayout.deleteItemView((View) v.getParent());
    }
}
