package com.aahl.hl_letter;
import com.aahl.hl_letter.model.AppUpDataBean;
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
 * @author : Mr.Hao
 * @date :  2018/6/19
 * @description : api模块 （开发时要注明对应接口的后端开发人员（XXX）://用户登录 -- XXX）
 */

public interface ApiSeverse {

    //APP版本信息查询 -- 天才1号
    @POST("/moses/app/appVersion/lastVersion")
    Flowable<HttpResultBean<AppUpDataBean>> lastVersion(@Body RequestBody params);

    //用户登录 -- 天才2号
    @POST("/moses/shop/app/user/login")
    Flowable<HttpResultBean<UserQueryBean>> login(@Body RequestBody params);

    //请求天气接口 -- 天才2号   cityId -- 城市
    @GET("data/cityinfo/{cityId}.html")
    Call<WeatherBean> requestWeather(@Path("cityId") String cityId);

}
