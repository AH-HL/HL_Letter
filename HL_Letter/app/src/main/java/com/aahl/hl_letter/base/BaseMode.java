package com.aahl.hl_letter.base;


import android.os.Message;

import com.aahl.hl_letter.config.EventBusConfig;
import com.aahl.hl_letter.model.UserQueryBean;
import com.aahl.hl_letter.remote.HttpResultBean;
import com.aahl.sdk_rxretrofit.http.RxSubscriber;

import org.greenrobot.eventbus.EventBus;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : Mr.Hao
 * @project : HLMVP
 * @date :  2017/12/13
 * @description : M层 数据层
 */

public class BaseMode<T> {
    private static final String BASE_URL = "http://devgw.vpclub.cn";
    private RxSubscriber<HttpResultBean<UserQueryBean>>  callback;

    public void request(Flowable<HttpResultBean<T>> flowable, RxSubscriber<HttpResultBean<T>> callback) {


        flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(500, TimeUnit.MILLISECONDS)//防抖动500ms
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        Message msg = new Message();
                        msg.what = EventBusConfig.REQUEST_LOADING;
                        EventBus.getDefault()
                                .post(msg);
                    }
                })
                .subscribe(callback);

    }


    /**
     * 取消网络请求
     */
    public void interruptHttp() {
        if (callback != null && !callback.isDisposed()) {
            callback.dispose();
        }
    }
}
