package com.aahl.sdk_rxretrofit.interceptor;


import com.aahl.sdk_rxretrofit.utils.NetLog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class LoggingInterceptor implements Interceptor {
	@Override
    public Response intercept(Chain chain) throws IOException {
		    Request request = chain.request();

		    long t1 = System.nanoTime();
		    NetLog.d("log", String.format("Sending request %s on %s%n%s",
					request.url(), chain.connection(), request.headers()));

		    Response response = chain.proceed(request);
		    long t2 = System.nanoTime();

			if (request.body() instanceof MultipartBody) {
				NetLog.d("log", String.format("Received response for %s in %.1fms%n%s",
						response.request().url(), (t2 - t1) / 1e6d, response.headers()));
			}else {
				NetLog.d("log", String.format("Received response for %s in %.1fms%n%sParam:%s",
						response.request().url(), (t2 - t1) / 1e6d, response.headers(), bodyToString(response.request().body())));
			}

			response.headers().get("Date");
		    return response;
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
}