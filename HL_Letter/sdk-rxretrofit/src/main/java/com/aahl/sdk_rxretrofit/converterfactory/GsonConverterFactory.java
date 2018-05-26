/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aahl.sdk_rxretrofit.converterfactory;

import com.aahl.sdk_rxretrofit.http.HttpClient;
import com.aahl.sdk_rxretrofit.utils.NetLog;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * A {@linkplain Converter.Factory converter} which uses Gson for JSON.
 * <p>
 * Because Gson is so flexible in the types it supports, this converter assumes that it can handle
 * all types. If you are mixing JSON serialization with something else (such as protocol buffers),
 * you must {@linkplain Retrofit.Builder#addConverterFactory(Converter.Factory) add this instance}
 * last to allow the other converters a chance to see their types.
 */
public final class GsonConverterFactory
        extends Converter.Factory {
  /**
   * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
   * decoding from JSON (when no charset is specified by a header) will use UTF-8.
   */
  public static GsonConverterFactory create() {
    return create(new Gson());
  }

  public static GsonConverterFactory create(MediaType mediaType) {

    return create(new Gson(),mediaType);
  }

  /**
   * Create an instance using {@code gson} for conversion. Encoding to JSON and
   * decoding from JSON (when no charset is specified by a header) will use UTF-8.
   */
  public static GsonConverterFactory create(Gson gson) {
    return new GsonConverterFactory(gson);
  }

  public static GsonConverterFactory create(Gson gson, MediaType mediaType) {
    return new GsonConverterFactory(gson, mediaType);
  }

  private final Gson gson;
  private final MediaType mediaType;

  private GsonConverterFactory(Gson gson) {
    if (gson == null) throw new NullPointerException("gson == null");
    this.gson = gson;
    this.mediaType = HttpClient.FORM_TYPE;
  }

  private GsonConverterFactory(Gson gson, MediaType mediaType) {
    if (gson == null) throw new NullPointerException("gson == null");
    this.gson = gson;
    this.mediaType = mediaType;
  }

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                          Retrofit retrofit) {
    TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
    return new GsonResponseBodyConverter<>(gson, adapter);
  }

  @Override
  public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                        Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
    TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
    return new GsonRequestBodyConverter<>(gson, adapter);
  }


  static final class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = HttpClient.FORM_TYPE;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final MediaType mediaType;


    GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
      this.gson = gson;
      this.adapter = adapter;
      this.mediaType = MEDIA_TYPE;
    }

    GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter, MediaType mediaType) {
      this.gson = gson;
      this.adapter = adapter;
      this.mediaType = mediaType;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
      Buffer buffer = new Buffer();
      Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
      JsonWriter jsonWriter = gson.newJsonWriter(writer);
      adapter.write(jsonWriter, value);
      jsonWriter.close();
      return RequestBody.create(mediaType, buffer.readByteString());
    }
  }


  final static class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
      this.gson = gson;
      this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

      try {
        byte[] bt = value.bytes();
        ByteBuffer bf = ByteBuffer.wrap(bt);
        ByteArrayInputStream bis = new ByteArrayInputStream(bf.array());
        Reader reader = new BufferedReader(new InputStreamReader(bis));
        JsonReader jsonReader = gson.newJsonReader(reader);
        NetLog.d("request-response",new String(bt,"utf-8"));
        T t = adapter.read(jsonReader);
//        if (t instanceof JsonObject) {
//          NetLog.d("request-response", "" + t.toString());
//        }else {
//          NetLog.d("request-response", "" + gson.toJson(t));
//        }
        bf.clear();
        reader.close();
        bis.close();
        return t;
      } finally {
        value.close();
      }
    }
  }


}
