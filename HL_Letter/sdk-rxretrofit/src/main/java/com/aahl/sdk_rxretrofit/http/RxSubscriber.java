package com.aahl.sdk_rxretrofit.http;


import android.support.annotation.NonNull;

import com.aahl.sdk_rxretrofit.utils.NetLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;


/**
 * 对话框操作处理
 * @param <T>
 */
public abstract class RxSubscriber<T> implements Subscriber<T> ,Disposable {

    public Subscription mDis ;

    @Override
    public void onSubscribe(@NonNull Subscription d) {
        mDis = d ;
        d.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        onComplete();
        NetLog.e("onError",e.getMessage()+"");
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }


}
