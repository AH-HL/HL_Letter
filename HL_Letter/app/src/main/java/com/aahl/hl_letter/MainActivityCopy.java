package com.aahl.hl_letter;

import android.os.Bundle;

import com.aahl.hl_letter.base.BaseMvpActivitiy;
import com.aahl.hl_letter.mvp.main.RequestPresenter;
import com.aahl.hl_letter.mvp.main.RequestView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author : Mr.Hao
 * @project : ${PROJECT_NAME}
 * @date :  ${DATE}
 * @description :
 */


public class MainActivityCopy extends BaseMvpActivitiy<RequestView, RequestPresenter> implements
        RequestView {

    // 保存用户按返回键的时间
    private long mExitTime = 0;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {



    }

    @Override
    public void requestLoading() {

    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {

    }

}
