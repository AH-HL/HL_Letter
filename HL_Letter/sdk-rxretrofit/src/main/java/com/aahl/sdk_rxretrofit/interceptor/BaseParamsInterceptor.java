package com.aahl.sdk_rxretrofit.interceptor;


import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;

import com.aahl.sdk_rxretrofit.http.HttpClient;
import com.aahl.sdk_rxretrofit.utils.NetLog;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 基本的拦截器，用来补充公共参数方案
 */
public class BaseParamsInterceptor implements Interceptor {

    private ArrayMap<String, String> queryParamsMap  = new ArrayMap<>();
    private ArrayMap<String, String> paramsMap       = new ArrayMap<>();
    private ArrayMap<String, String> headerParamsMap = new ArrayMap<>();
    private List<String> headerLinesList = new ArrayList<>();

    private MediaType mediaType ;//默认2者匹配

    protected BaseParamsInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        Headers.Builder headerBuilder = request.headers().newBuilder();
        if (headerParamsMap.size() > 0) {
            for (Object o : headerParamsMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }

        if (headerLinesList.size() > 0) {
            for (String line : headerLinesList) {
                headerBuilder.add(line);
            }
            requestBuilder.headers(headerBuilder.build());
        }

        if (queryParamsMap.size() > 0) {
            request = injectParamsIntoUrl(request.url().newBuilder(), requestBuilder, queryParamsMap);
        }

        String contentType = headerBuilder.get("Content-Type");
        NetLog.d("request-header",""+contentType);

        RequestBody requestBody =  request.body();

        //文件类型的不要动，如有文件就尴尬了
        if (paramsMap.size() > 0 &&(requestBody!=null && !(requestBody instanceof MultipartBody))||requestBody==null) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }

            RequestBody formBody = formBodyBuilder.build();
            String postBodyString = null;
            if (request != null) {
                postBodyString = bodyToString(request.body());
            }


            if (mediaType != null) {//强制指定 mediatype
                if (HttpClient.JSON_TYPE.subtype().equals(mediaType.subtype())) {
                    try {
                        if (TextUtils.isEmpty(postBodyString)) {
                            postBodyString = "{}";
                        }
                        JSONObject jsonObject = new JSONObject(postBodyString);
                        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                            jsonObject.put(entry.getKey(), entry.getValue());
                        }
                        NetLog.d("request-params", "" + jsonObject.toString());
                        requestBuilder.post(RequestBody.create(requestBody.contentType(), jsonObject.toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else if (HttpClient.FORM_TYPE.subtype().equals(mediaType.subtype())) {
                    if (postBodyString != null) {
                        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
                    }

                    NetLog.d("request-params", "" + postBodyString);
                    if (postBodyString != null) {
                        requestBuilder.post(RequestBody.create(requestBody.contentType(), postBodyString));
                    }
                }
            }else{ //两者匹配
                try {
                    JSONObject jsonObject = new JSONObject(postBodyString);
                    for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                        jsonObject.put(entry.getKey(), entry.getValue());
                    }
                    NetLog.d("request-params", "" + jsonObject.toString());
                    requestBuilder.post(RequestBody.create(HttpClient.JSON_TYPE, jsonObject.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    if (postBodyString != null) {
                        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
                    }

                    NetLog.d("request-params", "" + postBodyString);
                    if (postBodyString != null) {
                        requestBuilder.post(RequestBody.create(HttpClient.FORM_TYPE, postBodyString));
                    }
                }
            }




        }

        request = requestBuilder.build();
        return chain.proceed(request);
    }

    private Request injectParamsIntoUrl(HttpUrl.Builder httpUrlBuilder, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        if (paramsMap.size() > 0) {
            for (Object o : paramsMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            requestBuilder.url(httpUrlBuilder.build());
            return requestBuilder.build();
        }
        return null;
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null)
                request.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

   public static class Builder {

        BaseParamsInterceptor interceptor;

        public Builder() {
            interceptor = new BaseParamsInterceptor();
        }

        public Builder addParam(String key, String value) {
            interceptor.paramsMap.put(key, value);
            return this;
        }

        public Builder addParamsMap(Map<String, String> paramsMap) {
            interceptor.paramsMap.putAll(paramsMap);
            return this;
        }

        public Builder addHeaderParam(String key, String value) {
            interceptor.headerParamsMap.put(key, value);
            return this;
        }

       public Builder addDefaultMedia(MediaType mediaType) {
           interceptor.mediaType = mediaType;
           return this;
       }


        public Builder addHeaderParamsMap(Map<String, String> headerParamsMap) {
            interceptor.headerParamsMap.putAll(headerParamsMap);
            return this;
        }

        public Builder addHeaderLine(String headerLine) {
            int index = headerLine.indexOf(":");
            if (index == -1) {
                throw new IllegalArgumentException("Unexpected header: " + headerLine);
            }
            interceptor.headerLinesList.add(headerLine);
            return this;
        }

        public Builder addHeaderLinesList(List<String> headerLinesList) {
            for (String headerLine : headerLinesList) {
                int index = headerLine.indexOf(":");
                if (index == -1) {
                    throw new IllegalArgumentException("Unexpected header: " + headerLine);
                }
                interceptor.headerLinesList.add(headerLine);
            }
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            interceptor.queryParamsMap.put(key, value);
            return this;
        }

        public Builder addQueryParamsMap(SimpleArrayMap<String, String> queryParamsMap) {
            interceptor.queryParamsMap.putAll(queryParamsMap);
            return this;
        }

        public BaseParamsInterceptor build() {
            return interceptor;
        }
    }
}
