package com.aahl.sdk_rxretrofit.http;

import com.aahl.sdk_rxretrofit.converterfactory.GsonConverterFactory;
import com.aahl.sdk_rxretrofit.interceptor.BaseParamsInterceptor;
import com.aahl.sdk_rxretrofit.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * @author : Mr.Hao
 * @project : HLMVP
 * @date :  2017/12/14
 * @description :
 */

public class HttpClient {

    public static final String BASE_URL = "https://httpbin.org/";
    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FORM_TYPE = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
    private BaseParamsInterceptor baseParamsInterceptor;
    private volatile static HttpClient INSTANCE;
    private OkHttpClient okHttpClient;
    private Retrofit mRetrofit;
    private String mBaseUrl;

    //构造方法私有
    public HttpClient() {
    }

    //获取单例
    public static HttpClient getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpClient();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 创建 retorfit
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T createRetrofit(Class<T> tClass) {
        return getRetrofit().create(tClass);
    }


    /**
     * okhttp clicent
     *
     * @return
     */
    public synchronized OkHttpClient getOkHttpClient() {

        if (okHttpClient == null) {

            if (baseParamsInterceptor !=null){
                okHttpClient = new OkHttpClient.Builder().addInterceptor(baseParamsInterceptor).addInterceptor(new LoggingInterceptor())
                        .connectTimeout(15, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS)
                        .build();
            }else{
                okHttpClient = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor())
                        .connectTimeout(15, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS)
                        .build();
            }

        }
        return okHttpClient;
    }


    /**
     * 创建retrofit对象
     */
    private Retrofit getRetrofit() {

        if (mBaseUrl == null) {
            mBaseUrl = BASE_URL;
        }
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    //对okgttp的初始化
                    .client(okHttpClient)
                    //对返回字符串的支持，注意这个要写在gson之前
//                    .addConverterFactory(StringConverterFactory.create())
                    //对gson的支持
                    .addConverterFactory(GsonConverterFactory.create(FORM_TYPE))
                    //对rxjava的支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //基础的网络请求域名
                    .baseUrl(mBaseUrl)
                    .build();
        }
        return mRetrofit;

    }


    /**
     * 构建builder
     */
    public static class Builder {

        HttpClient netWorkFactory;

        public Builder() {
            netWorkFactory = new HttpClient();
        }

        public Builder addBaseUrl(String url) {
            netWorkFactory.mBaseUrl = url;
            return this;
        }

        public Builder addBasicParamInterceptor(BaseParamsInterceptor interceptor){
            netWorkFactory.baseParamsInterceptor = interceptor;
            return this ;
        }


        public Builder addOkHttpClient(OkHttpClient okHttpClient) {
            if (okHttpClient == null) {
                throw new IllegalArgumentException("okhttpclient is null !");
            }
            netWorkFactory.okHttpClient = okHttpClient;
            return this;
        }

        public Builder addRetrofit(Retrofit retrofit) {
            netWorkFactory.mRetrofit = retrofit;
            return this;
        }


        public HttpClient build() {
            if (netWorkFactory.okHttpClient == null) {
                netWorkFactory.okHttpClient = netWorkFactory.getOkHttpClient();
            }

            if (netWorkFactory.mRetrofit == null) {
                Retrofit retrofit = netWorkFactory.getRetrofit();
                netWorkFactory.mRetrofit = retrofit;
            }
            return netWorkFactory;
        }
    }


}
