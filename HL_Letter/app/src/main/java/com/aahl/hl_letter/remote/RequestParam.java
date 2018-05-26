package com.aahl.hl_letter.remote;


import com.aahl.sdk_rxretrofit.params.BaseRequestParams;

/**
 * @author : Mr.Hao
 * @date :  2018/5/26
 * @description :
 */

public class RequestParam extends BaseRequestParams {
    private static final String TAG = "RequestParam";

    public RequestParam() {

        put("appId", "100000054");

    }

    public RequestParam(boolean b) {

        put("appId", "100000054");

    }

}
