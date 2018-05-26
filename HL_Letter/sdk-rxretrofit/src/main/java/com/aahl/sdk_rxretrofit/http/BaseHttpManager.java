package com.aahl.sdk_rxretrofit.http;


import java.util.HashMap;

/**
 *  基础的网络请求接口
 */
public class BaseHttpManager {

    protected HttpClient httpManager;
    protected HashMap<String,Object> interIml = new HashMap<>();;//

    protected BaseHttpManager(){
    }

    /**
     * 重写可以自己配置更多
     * init data DataManager
     * @param baseUrl
     */
    public void init(String baseUrl){
        httpManager = new HttpClient.Builder().addBaseUrl(baseUrl).build();

    }

    /**
     * 获取接口清单类
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getInterIml(Class<T> tClass) {

        String key = tClass.getSimpleName();
        if (interIml.get(key)==null){
           interIml.put(key, httpManager.createRetrofit(tClass));
        }
        return (T)interIml.get(key);
    }








}
