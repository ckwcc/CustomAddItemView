package com.ckw.zfsoft.customaddviewlayout;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ckw.zfsoft.customaddviewlayout.TimePicker.PickerView;
import com.ckw.zfsoft.customaddviewlayout.TimePicker.ScreenUtil;

import java.util.ArrayList;

/**
 * Created by ckw
 * on 2017/12/23.
 */

public class SinglePickerView {

    public interface ResultHandler {
        void handle(String selectStr);
    }

    private String selectStr;

    private Context context;
    private ResultHandler handler;
    private Dialog seletorDialog;
    private PickerView pickerView;
    private ArrayList<String> list;

    private TextView tv_select,tv_cancel,tv_title;


    public SinglePickerView(Context context,ResultHandler resultHandler,ArrayList<String> list){
        this.context = context;
        this.handler = resultHandler;
        this.list = list;
        initDialog();
        initView();
    }

    private void initView() {
        pickerView = (PickerView) seletorDialog.findViewById(R.id.picker_view);
        tv_cancel = (TextView) seletorDialog.findViewById(R.id.tv_cancel);
        tv_select = (TextView) seletorDialog.findViewById(R.id.tv_select);
        tv_title = (TextView) seletorDialog.findViewById(R.id.tv_title);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seletorDialog.dismiss();
            }
        });

        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.handle(selectStr);
                seletorDialog.dismiss();
            }
        });
    }

    private void initDialog() {
        if(seletorDialog == null){
            seletorDialog = new Dialog(context,R.style.time_dialog);
            seletorDialog.setCancelable(false);
            seletorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            seletorDialog.setContentView(R.layout.dialog_single_picker_view);
            Window window = seletorDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = window.getAttributes();
            int width = ScreenUtil.getInstance(context).getScreenWidth();
            lp.width = width;
            window.setAttributes(lp);
        }
    }

    public void show(){
        initPickerView();
        seletorDialog.show();
    }

    /**
     * @param isLoop 是否循环展示，默认false
     */
    public void isLoop(boolean isLoop){
        this.pickerView.setIsLoop(isLoop);
    }

    private void initPickerView() {
        pickerView.setData(list);
        int size = list.size()/2;
        pickerView.setSelected(size);
        pickerView.setCanScroll(list.size() > 1);
        pickerView.setIsLoop(false);
        pickerView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                tv_title.setText(text);
                selectStr = text;
            }
        });

    }


}
