package com.aahl.hl_letter.mvp.main;


import com.aahl.sdk_mvp.view.BaseMvpView;

/**
 * @author : Mr.Hao
 * @project : HLMVP
 * @date :  2017/12/13
 * @description :
 */
//
public interface RequestView extends BaseMvpView {

    //请求时展示加载
    void requestLoading();
    //请求成功
    void resultSuccess(String result);
    //请求失败
    void resultFailure(String result);

}
