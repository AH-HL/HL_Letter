package com.aahl.hl_letter.mvp.flash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aahl.hl_letter.model.UserQueryBean;
import com.aahl.hl_letter.remote.HttpResultBean;
import com.aahl.sdk_mvp.presenter.BaseMvpPresenter;
import com.aahl.sdk_rxretrofit.http.RxSubscriber;


/**
 * @author : Mr.Hao
 * @project : HLMVP
 * @date :  2017/12/13
 * @description : P层  特点:需要持有M层和V层
 */

public class FlashPresenter extends BaseMvpPresenter<FlashView> {

    private final FlashMode mFlashMode;

    public FlashPresenter() {
        this.mFlashMode = new FlashMode();
    }

    public void clickRequest(final String cityId, final Context context) {
        //请求时显示加载
        if (getMvpView() != null) {
            getMvpView().requestLoading();
        }

        mFlashMode.request(cityId, new RxSubscriber<HttpResultBean<UserQueryBean>>() {
            @Override
            public void onNext(HttpResultBean<UserQueryBean> userQueryBeanHttpResultBean) {
                String  s  = "";
            }

            @Override
            public void onComplete() {
                getMvpView().resultFailure("登录");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                super.onError(e);
            }
        });

    }


    @Override
    public void onCreatePersenter(@Nullable Bundle savedState) {
        super.onCreatePersenter(savedState);
        if (savedState != null) {
            Log.e("sdk-mvp", "onCreatePersenter 测试  = " + savedState.getString("test2"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("sdk-mvp", "onSaveInstanceState 测试 ");
        outState.putString("test2", "test_save2");
    }

    @Override
    public void onDestroyPersenter() {
        super.onDestroyPersenter();
    }


    /**
     * 取消网络请求
     */
    public void interruptHttp() {
        mFlashMode.interruptHttp();
    }



}
