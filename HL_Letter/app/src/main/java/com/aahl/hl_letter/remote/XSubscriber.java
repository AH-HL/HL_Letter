package com.aahl.hl_letter.remote;

import android.os.Message;
import android.support.annotation.NonNull;

import com.aahl.hl_letter.config.EventBusConfig;
import com.aahl.sdk_rxretrofit.http.RxSubscriber;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by tp on 2016/12/22.
 */

public abstract class XSubscriber<T>
        extends RxSubscriber<T> {

    @Override
    public void onNext(T t) {
        Message msg = new Message();
        msg.what = EventBusConfig.REQUEST_LOADING_COLSE;
        EventBus.getDefault()
                .post(msg);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);
        Message msg = new Message();
        msg.what = EventBusConfig.REQUEST_LOADING_COLSE;
        EventBus.getDefault()
                .post(msg);
    }

    @Override
    public void onComplete() {

    }
}
