package com.aahl.data;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.aahl.sdk_utils.R;


/**
 * @author : Mr.Hao
 * @date :  2018/6/23
 * @description : DialogPicker
 */

public class DialogPicker extends LinearLayout implements
        OnePicker.OnOneSelectedListener, TwoPicker.OnTwoSelectedListener, ThreePicker.OnThreeSelectedListener {

    private OnePicker mOnePicker;
    private TwoPicker mTwoPicker;
    private ThreePicker mThreePicker;
    private OnTimeSelectedListener mOnTimeSelectedListener;

    public DialogPicker(Context context) {
        this(context, null);
    }

    public DialogPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.layout_date, this);
        initChild();
        initAttrs(context, attrs);

    }


    @Override
    public void onOneSelected(String SelectedOne) {
        onTimeSelected();
    }


    @Override
    public void onTwoSelected(String hour) {
        onTimeSelected();
    }

    @Override
    public void onThreeSelected(String hour) {
        onTimeSelected();
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DialogPicker);
        int textSize = a.getDimensionPixelSize(R.styleable.DialogPicker_itemTextSize,
                getResources().getDimensionPixelSize(R.dimen.WheelItemTextSize));
        int textColor = a.getColor(R.styleable.DialogPicker_itemTextColor,
                Color.BLACK);
        boolean isTextGradual = a.getBoolean(R.styleable.DialogPicker_textGradual, true);
        boolean isCyclic = a.getBoolean(R.styleable.DialogPicker_wheelCyclic, true);
        int halfVisibleItemCount = a.getInteger(R.styleable.DialogPicker_halfVisibleItemCount, 2);
        int selectedItemTextColor = a.getColor(R.styleable.DialogPicker_selectedTextColor,
                getResources().getColor(R.color.com_ycuwq_datepicker_selectedTextColor));
        int selectedItemTextSize = a.getDimensionPixelSize(R.styleable.DialogPicker_selectedTextSize,
                getResources().getDimensionPixelSize(R.dimen.WheelSelectedItemTextSize));
        int itemWidthSpace = a.getDimensionPixelSize(R.styleable.DialogPicker_itemWidthSpace,
                getResources().getDimensionPixelOffset(R.dimen.WheelItemWidthSpace));
        int itemHeightSpace = a.getDimensionPixelSize(R.styleable.DialogPicker_itemHeightSpace,
                getResources().getDimensionPixelOffset(R.dimen.WheelItemHeightSpace));
        boolean isZoomInSelectedItem = a.getBoolean(R.styleable.DialogPicker_zoomInSelectedItem, true);
        boolean isShowCurtain = a.getBoolean(R.styleable.DialogPicker_wheelCurtain, true);
        int curtainColor = a.getColor(R.styleable.DialogPicker_wheelCurtainColor, Color.WHITE);
        boolean isShowCurtainBorder = a.getBoolean(R.styleable.DialogPicker_wheelCurtainBorder, true);
        int curtainBorderColor = a.getColor(R.styleable.DialogPicker_wheelCurtainBorderColor,
                getResources().getColor(R.color.com_ycuwq_datepicker_divider));
        a.recycle();

        setTextSize(textSize);
        setTextColor(textColor);
        setTextGradual(isTextGradual);
        setCyclic(isCyclic);
        setHalfVisibleItemCount(halfVisibleItemCount);
        setSelectedItemTextColor(selectedItemTextColor);
        setSelectedItemTextSize(selectedItemTextSize);
        setItemWidthSpace(itemWidthSpace);
        setItemHeightSpace(itemHeightSpace);
        setZoomInSelectedItem(isZoomInSelectedItem);
        setShowCurtain(isShowCurtain);
        setCurtainColor(curtainColor);
        setShowCurtainBorder(isShowCurtainBorder);
        setCurtainBorderColor(curtainBorderColor);
    }
    private void initChild() {
        mOnePicker = findViewById(R.id.picker_one);
        mOnePicker.setOnHourSelectedListener(this);
        mTwoPicker = findViewById(R.id.picker_two);
        mTwoPicker.setOnTwoSelectedListener(this);
        mThreePicker = findViewById(R.id.picker_three);
        mThreePicker.setOnThreeSelectedListener(this);
    }

    private void onTimeSelected() {
        if (mOnTimeSelectedListener != null) {
            mOnTimeSelectedListener.onTimeSelected(getSelectedOne(), getSelectedTwo(),getSelectedThree());
        }
    }


    /**
     * Sets time.
     *
     * @param hour         the year
     * @param minute        the month
     */
    public void setTime(int hour, int minute) {
        setTime(hour, minute, true);
    }

    /**
     * Sets time.
     *
     * @param hour         the year
     * @param minute        the month
     * @param smoothScroll the smooth scroll
     */
    public void setTime(int hour, int minute, boolean smoothScroll) {
        mOnePicker.setSelectedOne(hour, smoothScroll);
        mTwoPicker.setSelectedTwo(minute, smoothScroll);
        mThreePicker.setSelectedThree(minute, smoothScroll);
    }

    /**
     * Gets hour.
     *
     * @return the hour
     */
    public String getSelectedOne() {
        return mOnePicker.getSelectedOne();
    }


    /**
     * Gets minuute.
     *
     * @return the minute
     */
    public String getSelectedTwo() {
        return mTwoPicker.getSelectedTwo();
    }


    /**
     * Gets minuute.
     *
     * @return the minute
     */
    public String getSelectedThree() {
        return mThreePicker.getSelectedThree();
    }


    public OnePicker getOnePicker() {
        return mOnePicker;
    }

    public TwoPicker getMinutePicker() {
        return mTwoPicker;
    }
    
    public ThreePicker getThreePicker() {
        return mThreePicker;
    }



    /**
     * 一般列表的文本颜色
     *
     * @param textColor 文本颜色
     */
    public void setTextColor(@ColorInt int textColor) {
        mOnePicker.setTextColor(textColor);
        mTwoPicker.setTextColor(textColor);
        mThreePicker.setTextColor(textColor);
    }

    /**
     * 一般列表的文本大小
     *
     * @param textSize 文字大小
     */
    public void setTextSize(int textSize) {
        mOnePicker.setTextSize(textSize);
        mTwoPicker.setTextSize(textSize);
        mThreePicker.setTextSize(textSize);
    }

    /**
     * 设置被选中时候的文本颜色
     *
     * @param selectedItemTextColor 文本颜色
     */
    public void setSelectedItemTextColor(@ColorInt int selectedItemTextColor) {
        mOnePicker.setSelectedItemTextColor(selectedItemTextColor);
        mTwoPicker.setSelectedItemTextColor(selectedItemTextColor);
        mThreePicker.setSelectedItemTextColor(selectedItemTextColor);
    }

    /**
     * 设置被选中时候的文本大小
     *
     * @param selectedItemTextSize 文字大小
     */
    public void setSelectedItemTextSize(int selectedItemTextSize) {
        mOnePicker.setSelectedItemTextSize(selectedItemTextSize);
        mTwoPicker.setSelectedItemTextSize(selectedItemTextSize);
        mThreePicker.setSelectedItemTextSize(selectedItemTextSize);
    }


    /**
     * 设置显示数据量的个数的一半。
     * 为保证总显示个数为奇数,这里将总数拆分，itemCount = mHalfVisibleItemCount * 2 + 1
     *
     * @param halfVisibleItemCount 总数量的一半
     */
    public void setHalfVisibleItemCount(int halfVisibleItemCount) {
        mOnePicker.setHalfVisibleItemCount(halfVisibleItemCount);
        mTwoPicker.setHalfVisibleItemCount(halfVisibleItemCount);
        mThreePicker.setHalfVisibleItemCount(halfVisibleItemCount);
    }

    /**
     * Sets item width space.
     *
     * @param itemWidthSpace the item width space
     */
    public void setItemWidthSpace(int itemWidthSpace) {
        mOnePicker.setItemWidthSpace(itemWidthSpace);
        mTwoPicker.setItemWidthSpace(itemWidthSpace);
        mThreePicker.setItemWidthSpace(itemWidthSpace);
    }

    /**
     * 设置两个Item之间的间隔
     *
     * @param itemHeightSpace 间隔值
     */
    public void setItemHeightSpace(int itemHeightSpace) {
        mOnePicker.setItemHeightSpace(itemHeightSpace);
        mTwoPicker.setItemHeightSpace(itemHeightSpace);
        mThreePicker.setItemHeightSpace(itemHeightSpace);
    }


    /**
     * Set zoom in center item.
     *
     * @param zoomInSelectedItem the zoom in center item
     */
    public void setZoomInSelectedItem(boolean zoomInSelectedItem) {
        mOnePicker.setZoomInSelectedItem(zoomInSelectedItem);
        mTwoPicker.setZoomInSelectedItem(zoomInSelectedItem);
        mThreePicker.setZoomInSelectedItem(zoomInSelectedItem);
    }

    /**
     * 设置是否循环滚动。
     * set wheel cyclic
     * @param cyclic 上下边界是否相邻
     */
    public void setCyclic(boolean cyclic) {
        mOnePicker.setCyclic(cyclic);
        mTwoPicker.setCyclic(cyclic);
        mThreePicker.setCyclic(cyclic);
    }

    /**
     * 设置文字渐变，离中心越远越淡。
     * Set the text color gradient
     * @param textGradual 是否渐变
     */
    public void setTextGradual(boolean textGradual) {
        mOnePicker.setTextGradual(textGradual);
        mTwoPicker.setTextGradual(textGradual);
        mThreePicker.setTextGradual(textGradual);
    }


    /**
     * 设置中心Item是否有幕布遮盖
     * set the center item curtain cover
     * @param showCurtain 是否有幕布
     */
    public void setShowCurtain(boolean showCurtain) {
        mOnePicker.setShowCurtain(showCurtain);
        mTwoPicker.setShowCurtain(showCurtain);
        mThreePicker.setShowCurtain(showCurtain);
    }

    /**
     * 设置幕布颜色
     * set curtain color
     * @param curtainColor 幕布颜色
     */
    public void setCurtainColor(@ColorInt int curtainColor) {
        mOnePicker.setCurtainColor(curtainColor);
        mTwoPicker.setCurtainColor(curtainColor);
        mThreePicker.setCurtainColor(curtainColor);
    }

    /**
     * 设置幕布是否显示边框
     * set curtain border
     * @param showCurtainBorder 是否有幕布边框
     */
    public void setShowCurtainBorder(boolean showCurtainBorder) {
        mOnePicker.setShowCurtainBorder(showCurtainBorder);
        mTwoPicker.setShowCurtainBorder(showCurtainBorder);
        mThreePicker.setShowCurtainBorder(showCurtainBorder);
    }

    /**
     * 幕布边框的颜色
     * curtain border color
     * @param curtainBorderColor 幕布边框颜色
     */
    public void setCurtainBorderColor(@ColorInt int curtainBorderColor) {
        mOnePicker.setCurtainBorderColor(curtainBorderColor);
        mTwoPicker.setCurtainBorderColor(curtainBorderColor);
        mThreePicker.setCurtainBorderColor(curtainBorderColor);
    }

    /**
     * 设置选择器的指示器文本
     * set indicator text
     * @param hourText  小时指示器文本
     * @param minuteText 分钟指示器文本

     */
    public void setIndicatorText(String hourText, String minuteText) {
        mOnePicker.setIndicatorText(hourText);
        mTwoPicker.setIndicatorText(minuteText);
        mThreePicker.setIndicatorText(minuteText);
    }

    /**
     * 设置指示器文字的颜色
     * set indicator text color
     * @param textColor 文本颜色
     */
    public void setIndicatorTextColor(@ColorInt int textColor) {
        mOnePicker.setIndicatorTextColor(textColor);
        mTwoPicker.setIndicatorTextColor(textColor);
        mThreePicker.setIndicatorTextColor(textColor);
    }

    /**
     * 设置指示器文字的大小
     *  indicator text size
     * @param textSize 文本大小
     */
    public void setIndicatorTextSize(int textSize) {
        mOnePicker.setTextSize(textSize);
        mTwoPicker.setTextSize(textSize);
        mThreePicker.setTextSize(textSize);
    }

    /**
     * Sets on date selected listener.
     *
     * @param onTimeSelectedListener the on time selected listener
     */
    public void setOnTimeSelectedListener(OnTimeSelectedListener onTimeSelectedListener) {
        mOnTimeSelectedListener = onTimeSelectedListener;
    }


    /**
     * The interface On date selected listener.
     */
    public interface OnTimeSelectedListener {
        /**
         * On time selected.
         *
         */
        void onTimeSelected(String one, String two, String three);
    }
}
