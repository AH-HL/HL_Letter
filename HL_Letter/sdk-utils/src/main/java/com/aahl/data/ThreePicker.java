package com.aahl.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.aahl.sdk_utils.DateUtils;

import java.text.NumberFormat;
import java.util.List;

/**
 * HourPicker
 * Created by ycuwq on 2018/1/22.
 */
public class ThreePicker extends WheelPicker<String>{
    private String mSelectedThree;
    private OnThreeSelectedListener mOnThreeSelectedListener;
    private List<String> mHourList;

    public ThreePicker(Context context) {
        this(context, null);
    }

    public ThreePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThreePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                setSelectedThree(i + 2,false);
            }
        }
        setOnWheelChangeListener(new OnWheelChangeListener<String>() {
            @Override
            public void onWheelSelected(String item, int position) {
                mSelectedThree = item;
                if (mOnThreeSelectedListener != null) {
                    mOnThreeSelectedListener.onThreeSelected(item);
                }
            }
        });
    }

    private void updateHour() {
        /** 获取一天之内的任意整数时段   注意：开始时间要小于结束时间，且在0~23范围内 */
        mHourList = DateUtils.getHourList(11, 20);
        setDataList(mHourList);
    }

    public String getSelectedThree() {
        return mHourList.get(getCurrentPosition());
    }

    public void setSelectedThree(int three) {
        setSelectedThree(three, true);
    }

    public void setSelectedThree(int three, boolean smootScroll) {
        setCurrentPosition(three, smootScroll);
    }

    public void setOnThreeSelectedListener(OnThreeSelectedListener onThreeSelectedListener) {
        mOnThreeSelectedListener = onThreeSelectedListener;
    }

    public interface OnThreeSelectedListener {
        void onThreeSelected(String hour);
    }
}
