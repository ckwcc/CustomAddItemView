package com.ckw.zfsoft.customaddviewlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckw
 * on 2017/12/18.
 */

public class CustomAddViewLayout extends ViewGroup {

    private List<String> mItemTexts = new ArrayList<>();

    public CustomAddViewLayout(Context context) {
        this(context,null);
    }

    public CustomAddViewLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomAddViewLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
///////////////////////////////////////////////////////////////////////////////////////////////
    //单选类型
    public void setAllRadioButtonUnchecked(){
        int count = getChildCount();
        for (int i = 0; i <count; i++) {
            RelativeLayout item = (RelativeLayout) getChildAt(i);
            CheckBox radioButton = (CheckBox) item.getChildAt(0);
            radioButton.setChecked(false);
        }
    }

    public int getSelectedRadioButtonIndex(){
        int count = getChildCount();
        for (int i = 0; i <count; i++) {
            RelativeLayout item = (RelativeLayout) getChildAt(i);
            CheckBox radioButton = (CheckBox) item.getChildAt(0);
            if(radioButton.isChecked()){
                return i;
            }
        }
        return -1;
    }
///////////////////////////////////////////////////////////////////////////////////////////////
    //文本类型
    /**
     *
     * @return 所有item文本
     */
    public List<String> getAllItemText(){
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            RelativeLayout item = (RelativeLayout) getChildAt(i);
            EditText editText = (EditText) item.getChildAt(1);
            String s = editText.getText().toString();
            mItemTexts.add(s);
        }
        return mItemTexts;
    }

    /**
     * @return 只要item里面有一个没有内容，就返回false
     */
    public boolean checkAllItemText(){
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            RelativeLayout item = (RelativeLayout) getChildAt(i);
            EditText editText = (EditText) item.getChildAt(1);
            String s = editText.getText().toString();
            if("".equals(s)){
                return false;
            }
        }
        return true;

    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    public void addItemView(View view){
        addView(view);
        requestLayout();
        invalidate();
    }

    public void deleteItemView(int index){
        removeViewAt(index);
        requestLayout();
        invalidate();
    }

    public void deleteItemView(View view){
        removeView(view);
        requestLayout();
        invalidate();
    }

    public void deleteAllView(){
        removeAllViews();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //建议的长宽，但还不是最终的，由模式决定
        int suggestWidth = MeasureSpec.getSize(widthMeasureSpec);
        int suggestHeight = MeasureSpec.getSize(heightMeasureSpec);

        int count = getChildCount();
        int childHeight = 0;
        int totalHeight = 0;
        int resultHeight = suggestHeight;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            getChildAt(i).measure(widthMeasureSpec,heightMeasureSpec);
            childHeight = child.getMeasuredHeight();
            if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED){
                totalHeight += getChildAt(i).getMeasuredHeight();
            }
        }

        if(heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED){
            if(totalHeight>suggestHeight && suggestHeight != 0){
                resultHeight = suggestHeight;
            }else {
                resultHeight = totalHeight;
            }
        }

        setMeasuredDimension(suggestWidth,resultHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int totalHeight = 0;//子view在y轴上的bottom
        int currentTop = getPaddingTop();//子view在y轴上的top
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight();
            totalHeight += childHeight;

            child.layout(getPaddingLeft(),currentTop,child.getMeasuredWidth(),totalHeight);
            currentTop += childHeight;
        }
    }
}
