package com.aahl.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.aahl.sdk_utils.DateUtils;

import java.text.NumberFormat;
import java.util.List;

/**
 * @author : Mr.Hao
 * @date :  2018/6/23
 * @description : 可自定义任意日期 如  12:00
 */

public class TwoPicker extends WheelPicker<String>{

    private String mSelectedTwo;
    private OnTwoSelectedListener mOnTwoSelectedListener;
    private List<String> mHourList;

    public TwoPicker(Context context) {
        this(context, null);
    }

    public TwoPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwoPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemMaximumWidthText("00:00");
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(2);
        setDataFormat(null);
        updateHour();
        /** 获取当前日期 PATTERN_TIME 部分 "HH:mm:ss" */
        String shortTime = DateUtils.getDayTime();
        String hour = shortTime.split(":")[0];//这里取当前需要的小时
        List<String> dataList = getDataList();
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).equals(hour+":00")){
                setSelectedTwo(i,false);
            }
        }
        setOnWheelChangeListener(new OnWheelChangeListener<String>() {
            @Override
            public void onWheelSelected(String item, int position) {
                mSelectedTwo = item;
                if (mOnTwoSelectedListener != null) {
                    mOnTwoSelectedListener.onTwoSelected(item);
                }
            }
        });
    }

    private void updateHour() {
        /** 获取一天之内的任意整数时段   注意：开始时间要小于结束时间，且在0~23范围内 */
        mHourList = DateUtils.getHourList(9, 18);
        setDataList(mHourList);
    }

    public String getSelectedTwo() {
        return mHourList.get(getCurrentPosition());
    }

    public void setSelectedTwo(int hour) {
        setSelectedTwo(hour, true);
    }

    public void setSelectedTwo(int hour, boolean smootScroll) {
        setCurrentPosition(hour, smootScroll);
    }

    public void setOnTwoSelectedListener(OnTwoSelectedListener onTwoSelectedListener) {
        mOnTwoSelectedListener = onTwoSelectedListener;
    }

    public interface OnTwoSelectedListener {
        void onTwoSelected(String hour);
    }
}
