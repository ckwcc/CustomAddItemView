package com.ckw.zfsoft.customaddviewlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 使用添加一行自定义控件
 * 实现单选 和 多选功能
 */
public class RadioButtonActivity extends AppCompatActivity {

    private CustomAddViewLayout mCustomAddViewLayout;
    private Button mJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        mCustomAddViewLayout = findViewById(R.id.customLayout);
        mJump = findViewById(R.id.btn_jump);

        mJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RadioButtonActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        //单选功能
        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_radio_button, null);
            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.radio_button);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomAddViewLayout.setAllRadioButtonUnchecked();
                    checkBox.setChecked(true);
                    int selectedRadioButtonIndex = mCustomAddViewLayout.getSelectedRadioButtonIndex() + 1;
                }
            });

            TextView radioText = (TextView) view.findViewById(R.id.radio_text);
            radioText.setText("哈哈");
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50);
            view.setLayoutParams(layoutParams);
            mCustomAddViewLayout.addItemView(view);
        }

//        for (int i = 0; i < 3; i++) {
//            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_radio_button, null);
//            final CheckBox radioButton = (CheckBox) view.findViewById(R.id.radio_button);
//            radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("----", "onClick: 选中状态："+radioButton.isChecked());
//
//                }
//            });
//
//            TextView radioText = (TextView) view.findViewById(R.id.radio_text);
//            radioText.setText("哈哈");
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50);
//            view.setLayoutParams(layoutParams);
//            mCustomAddViewLayout.addItemView(view);
//        }
    }


}
