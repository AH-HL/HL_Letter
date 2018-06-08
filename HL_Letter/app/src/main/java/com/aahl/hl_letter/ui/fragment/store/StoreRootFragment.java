package com.aahl.hl_letter.ui.fragment.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.aahl.hl_letter.R;
import com.aahl.hl_letter.base.BaseCompatFragment;

/**
 * @author : Mr.Hao
 * @date :  2018/6/8
 * @description :
 */

public class StoreRootFragment extends BaseCompatFragment {

    public static StoreRootFragment newInstance() {
        Bundle args = new Bundle();
        StoreRootFragment fragment = new StoreRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(StoreFragment.class) == null) {
            loadRootFragment(R.id.fl_container, StoreFragment.newInstance());
        }
    }


}
