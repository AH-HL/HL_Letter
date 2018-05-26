package com.aahl.sdk_rxretrofit.converterfactory;


import com.aahl.sdk_rxretrofit.http.HttpClient;
import com.aahl.sdk_rxretrofit.utils.NetLog;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author : Mr.Hao
 * @project : HLMVP
 * @date :  2017/12/15
 * @description :
 */
public class StringConverterFactory extends Converter.Factory {

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    public static StringConverterFactory create(MediaType mediaType) {
        return new StringConverterFactory(mediaType);
    }

    MediaType mediaType;

    private StringConverterFactory(MediaType mediaType) {
        this.mediaType = mediaType;
    }


    private StringConverterFactory() {

    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new StringResponseBodyConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new StringRequestBodyConverter();
    }
    
    
    /**
     * Created by  
     */
    public static class StringRequestBodyConverter  implements Converter<String, RequestBody> {
        private static final Charset UTF_8 = Charset.forName("UTF-8");


        MediaType mediaType;

        private StringRequestBodyConverter(MediaType mediaType) {
            this.mediaType = mediaType;
        }


        StringRequestBodyConverter() {
        }

        @Override
        public RequestBody convert(String value) throws IOException {
        	
        	NetLog.d("netrequest", ""+value);
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            writer.write(value);
            writer.close();
            if (mediaType ==null){
                mediaType = HttpClient.FORM_TYPE;
            }
            return RequestBody.create(mediaType, buffer.readByteString());
        }
    }
    

	/**
	 * Created by  
	 */
	public static class StringResponseBodyConverter implements Converter<ResponseBody, String> {
	    @Override
	    public String convert(ResponseBody value) throws IOException {
	        try {
	            return value.string();
	        } finally {
	            value.close();
	        }
	    }
}
}
