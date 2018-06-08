package com.aahl.hl_letter.ui.fragment.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.aahl.hl_letter.R;
import com.aahl.hl_letter.base.BaseCompatFragment;



/**
 * Created by Horrarndoo on 2017/9/7.
 * <p>
 */

public class MyRootFragment extends BaseCompatFragment {

    public static MyRootFragment newInstance() {
        Bundle args = new Bundle();
        MyRootFragment fragment = new MyRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(MyFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MyFragment.newInstance());
        }
    }


}
