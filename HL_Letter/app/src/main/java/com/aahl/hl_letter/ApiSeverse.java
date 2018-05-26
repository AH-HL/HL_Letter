package com.aahl.hl_letter;


import com.aahl.hl_letter.model.UserQueryBean;
import com.aahl.hl_letter.model.WeatherBean;
import com.aahl.hl_letter.remote.HttpResultBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * XXX服务器
 */
public interface ApiSeverse {

    //用户登录
    @POST("/moses/shop/app/user/login")
    Flowable<HttpResultBean<UserQueryBean>> login(@Body RequestBody params);

    /**
     * 请求天气接口
     * @param cityId 城市
     * @return Call
     */
    @GET("data/cityinfo/{cityId}.html")
    Call<WeatherBean> requestWeather(@Path("cityId") String cityId);

}
