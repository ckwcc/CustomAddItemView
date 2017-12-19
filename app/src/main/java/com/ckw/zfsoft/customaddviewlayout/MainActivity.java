package com.ckw.zfsoft.customaddviewlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomAddViewLayout mCustomAddViewLayout;
    private Button mAdd;
    private Button mRemoveAll;
    private int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomAddViewLayout = findViewById(R.id.customView);
        mAdd = findViewById(R.id.btn_add);
        mRemoveAll = findViewById(R.id.btn_removeAll);

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
