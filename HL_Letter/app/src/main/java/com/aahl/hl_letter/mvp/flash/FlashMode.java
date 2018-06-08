package com.aahl.hl_letter.mvp.flash;


import com.aahl.hl_letter.base.BaseMode;
import com.aahl.hl_letter.model.AppUpDataBean;
import com.aahl.hl_letter.remote.HttpResultBean;
import com.aahl.hl_letter.remote.RequestParam;
import com.aahl.hl_letter.remote.manager.DataManager;
import com.aahl.sdk_rxretrofit.http.RxSubscriber;
import com.aahl.sdk_rxretrofit.params.BaseRequestParams;

import io.reactivex.Flowable;

/**
 * @author : Mr.Hao
 * @project : HLMVP
 * @date :  2017/12/13
 * @description : M层 数据层
 */

public class FlashMode<T> extends BaseMode{

    private RxSubscriber<HttpResultBean<T>>  callback;

    public void request( RxSubscriber<HttpResultBean<T>> callback) {
        this.callback = callback;
        //请求入参
        BaseRequestParams params = new RequestParam();
        params.put("deviceType", 2);

        Flowable<HttpResultBean<AppUpDataBean>> httpResultBeanFlowable = DataManager.XsbServ()
                .lastVersion(params.createRequestBody());
        request(httpResultBeanFlowable,callback);

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
