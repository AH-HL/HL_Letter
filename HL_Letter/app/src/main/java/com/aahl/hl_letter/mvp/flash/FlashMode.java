package com.aahl.hl_letter.mvp.flash;


import com.aahl.hl_letter.model.UserQueryBean;
import com.aahl.hl_letter.remote.HttpResultBean;
import com.aahl.hl_letter.remote.RequestParam;
import com.aahl.hl_letter.remote.manager.DataManager;
import com.aahl.hl_letter.utils.MD5Util;
import com.aahl.sdk_rxretrofit.http.RxSubscriber;
import com.aahl.sdk_rxretrofit.params.BaseRequestParams;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : Mr.Hao
 * @project : HLMVP
 * @date :  2017/12/13
 * @description : M层 数据层
 */

public class FlashMode {
    private static final String BASE_URL = "http://devgw.vpclub.cn";
    private RxSubscriber<HttpResultBean<UserQueryBean>>  callback;

    public void request(String detailId, RxSubscriber<HttpResultBean<UserQueryBean>> callback) {
        this.callback = callback;
        //请求接口
        BaseRequestParams params = new RequestParam();
        params.put("loginPhone", "13692169349");//"13266811623" AesResultUtils.getAesResult("")
        params.put("password", MD5Util.MD532("169349"));
        params.put("smsCode", "1234");

        DataManager.XsbServ()
                .login(params.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
