package com.aahl.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.aahl.sdk_utils.DateUtils;
import com.aahl.sdk_utils.TypeConvertUtils;

import java.text.NumberFormat;
import java.util.List;

/**
 * @author : Mr.Hao
 * @date :  2018/6/23
 * @description : 主要做yyyyMMdd类型日期用
 */

public class OnePicker extends WheelPicker<String>{

    private String mSelectedOne;
    private OnOneSelectedListener mOnOneSelectedListener;
    private List<String> mListData;

    public OnePicker(Context context) {
        this(context, null);
    }

    public OnePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemMaximumWidthText("00:00");
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(2);
        setDataFormat(null);
        updateOneList();
        String curDate = DateUtils.getCurDate().replace("-", "");
        int curDateInt = TypeConvertUtils.stringToInt(curDate, 2018) +1;
        setSelectedOne(0,false);
        setOnWheelChangeListener(new OnWheelChangeListener<String>() {
            @Override
            public void onWheelSelected(String item, int position) {
                mSelectedOne = item;
                if (mOnOneSelectedListener != null) {
                    mOnOneSelectedListener.onOneSelected(item);
                }
            }
        });
    }

    private void updateOneList() {
        /** 获取当前时间起,累计XX天的日期（yyyy-MM-dd）数组 （ 已经转换为我们需要的格式：yyyyMMdd  ）*/
        mListData = DateUtils.getListData(7);
        setDataList(mListData);
    }


    public String getSelectedOne() {
        return   mListData.get(getCurrentPosition());
    }


    public void setSelectedOne(int selectedOne) {
        setSelectedOne(selectedOne, false);
    }

    public void setSelectedOne(int selectedOne, boolean smootScroll) {
        setCurrentPosition(selectedOne, smootScroll);
    }

    public void setOnHourSelectedListener(OnOneSelectedListener onHourSelectedListener) {
        mOnOneSelectedListener = onHourSelectedListener;
    }

    public interface OnOneSelectedListener {
        void onOneSelected(String SelectedOne);
    }
}
