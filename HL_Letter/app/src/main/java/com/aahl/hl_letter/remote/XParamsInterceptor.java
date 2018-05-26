package com.aahl.hl_letter.remote;


import com.aahl.sdk_rxretrofit.interceptor.BaseParamsInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tp on 2017/9/13.
 *
 * 参数拦截器
 */

public class XParamsInterceptor extends BaseParamsInterceptor {

    public XParamsInterceptor() {
        super();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        //添加公共参数
//        LoginBean bean = Hawk.get("LoginBean");
//        if (bean != null) {//token
//            builder.addHeader("Authorization", bean.getJwtToken()+"");
//        }
//        request = builder.build();

//        NetLog.d("log", String.format("request for %s in %.1fms%n%s",
//                    request.url(), 0 / 1e6d, request.headers()));


        return chain.proceed(request);
    }
}
