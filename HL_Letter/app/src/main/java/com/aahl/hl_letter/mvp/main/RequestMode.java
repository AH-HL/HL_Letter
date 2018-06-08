package com.aahl.hl_letter.mvp.main;


import com.aahl.hl_letter.model.UserQueryBean;
import com.aahl.hl_letter.model.WeatherBean;
import com.aahl.hl_letter.remote.HttpResultBean;
import com.aahl.hl_letter.remote.RequestParam;
import com.aahl.hl_letter.remote.manager.DataManager;
import com.aahl.hl_letter.utils.MD5Util;
import com.aahl.sdk_rxretrofit.http.RxSubscriber;
import com.aahl.sdk_rxretrofit.params.BaseRequestParams;

import org.reactivestreams.Subscriber;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

/**
 * @author : Mr.Hao
 * @project : HLMVP
 * @date :  2017/12/13
 * @description : M层 数据层
 */

public class RequestMode {
    private static final String BASE_URL = "http://devgw.vpclub.cn";
    private Call<WeatherBean> weatherBeanCall;
    private Subscriber<String> subscriber;


    //http://www.weather.com.cn/data/cityinfo/101010100.html
    public void request(String detailId, RxSubscriber<HttpResultBean<UserQueryBean>> callback) {
        //请求接口
//        Retrofit retrofit = new Retrofit.Builder()
//                //代表root地址
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        //请求
//        weatherBeanCall = apiService.requestWeather(detailId);
//        weatherBeanCall.enqueue(callback);


        // subscribeOn():指定 subscribe() 所发生的线程
        //  observeOn(): 指定 Subscriber 所运行在的线程
//        DataManager.XsbServ()
//
//                .requestWeather(detailId)
//                .enqueue(callback);


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
        if (weatherBeanCall != null && !weatherBeanCall.isCanceled()) {
            weatherBeanCall.cancel();
        }
    }
}
