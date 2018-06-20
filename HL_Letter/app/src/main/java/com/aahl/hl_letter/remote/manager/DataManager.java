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
public class DataManager
        extends BaseHttpManager
{


    private static  DataManager dataManager ;


    DataManager() {
        super();
    }

    public synchronized static DataManager newInstance() {
        if (dataManager == null){
            dataManager = new DataManager();
            dataManager.init(Contents.URL_HTTP);
        }
        return  dataManager;
    }

    public synchronized static DataManager newInstance(String baseUrl) {
        if (dataManager == null){
            dataManager = new DataManager();
            dataManager.init(baseUrl);
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
