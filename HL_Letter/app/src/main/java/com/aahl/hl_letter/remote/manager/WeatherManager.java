package com.aahl.hl_letter.remote.manager;


import com.aahl.hl_letter.ApiSeverse;
import com.aahl.hl_letter.remote.Contents;
import com.aahl.hl_letter.remote.XParamsInterceptor;
import com.aahl.sdk_rxretrofit.http.BaseHttpManager;
import com.aahl.sdk_rxretrofit.http.HttpClient;

/**
 * Created by tp on 2016/12/21.
 * 管理所有的接口列表
 */
public class WeatherManager
        extends BaseHttpManager
{


    private static WeatherManager dataManager ;


    WeatherManager() {
        super();
    }

    public synchronized static WeatherManager newInstance() {
        if (dataManager == null){
            dataManager = new WeatherManager();
            dataManager.init(Contents.URL_HTTP_WEATHER);
        }
        return  dataManager;
    }

    @Override
    public void init(String baseUrl) {
        httpManager = new HttpClient.Builder().addBaseUrl(baseUrl).addBasicParamInterceptor(new XParamsInterceptor()).build();
    }

    /**
     * 获取接口
     */
    public static ApiSeverse XsbServ(){
        return newInstance().getInterIml(ApiSeverse.class);
    }


}
