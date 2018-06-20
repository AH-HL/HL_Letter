package com.aahl.hl_letter.mvp.flash;


import com.aahl.sdk_mvp.view.BaseMvpView;

/**
 * @author : Mr.Hao
 * @date :  2017/12/13
 * @description :
 */
//
public interface FlashView extends BaseMvpView {

    //请求时展示加载
    void requestLoading();
    //请求成功
    void resultSuccess(String result);
    //请求失败
    void resultFailure(String result);
    //倒计时数字展示
    void showJumpTime(String time);
    //跳转activity
    void jumpActivity(Class<?> clz);

}
