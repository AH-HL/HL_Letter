package com.aahl.hl_letter.ui.fragment.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.aahl.hl_letter.R;
import com.aahl.hl_letter.base.BaseMvpFragment;
import com.aahl.hl_letter.mvp.main.RequestPresenter;
import com.aahl.hl_letter.mvp.main.RequestView;

/**
 * Created by Horrarndoo on 2017/9/11.
 * <p>
 */

public class MyFragment extends BaseMvpFragment<RequestView, RequestPresenter> implements RequestView {


    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

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
