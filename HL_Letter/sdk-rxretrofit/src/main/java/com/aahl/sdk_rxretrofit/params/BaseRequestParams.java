/*
    Android Asynchronous Http Client
    Copyright (c) 2011 James Smith <james@loopj.com>
    https://loopj.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.aahl.sdk_rxretrofit.params;

import android.text.TextUtils;

import com.aahl.sdk_rxretrofit.utils.NetLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author tp

 * A collection of string request parameters or files to send along with requests made from an
 * <pre>
 * RequestParams params = new RequestParams();
 * params.put("username", "james");
 * params.put("password", "123456");
 * params.put("email", "my&#064;email.com");
 * params.put("profile_picture", new File("pic.jpg")); // Upload a File
 * params.put("profile_picture2", someInputStream); // Upload an InputStream
 * params.put("profile_picture3", new ByteArrayInputStream(someBytes)); // Upload some bytes
 *
 * Map&lt;String, String&gt; map = new HashMap&lt;String, String&gt;();
 * map.put("first_name", "James");
 * map.put("last_name", "Smith");
 * params.put("user", map); // url params: "user[first_name]=James&amp;user[last_name]=Smith"
 *
 * Set&lt;String&gt; set = new HashSet&lt;String&gt;(); // unordered collection
 * set.add("music");
 * set.add("art");
 * params.put("like", set); // url params: "like=music&amp;like=art"
 *
 * List&lt;String&gt; list = new ArrayList&lt;String&gt;(); // Ordered collection
 * list.add("Java");
 * list.add("C");
 * params.put("languages", list); // url params: "languages[0]=Java&amp;languages[1]=C"

 * </pre>
 */
public class BaseRequestParams implements Serializable {


    protected final static String LOG_TAG = "RequestParams";
    protected final ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();
    protected final ConcurrentHashMap<String, FileWrapper> fileParams = new ConcurrentHashMap<String, FileWrapper>();
    protected final ConcurrentHashMap<String, String> heads = new ConcurrentHashMap<String, String>();
    protected String jsonParams ;
    protected boolean isJsonParams =true;

    protected boolean isRepeatable = true; //是否 重复尝试
    protected String contentEncoding = "utf-8";

    protected boolean isShowDialog = true ;//是否显示对话框

    /**
     * Constructs a new empty {@code RequestParams} instance.
     */
    public BaseRequestParams() {
        this((Map<String, String>) null);
    }

    /**
     * Constructs a new RequestParams instance containing the key/value string params from the
     * specified map.
     *
     * @param source the source key/value string map to add.
     */
    public BaseRequestParams(Map<String, String> source) {
        if (source != null) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Constructs a new RequestParams instance and populate it with a single initial key/value
     * string param.
     *
     * @param key   the key name for the intial param.
     * @param value the value string for the initial param.
     */
    public BaseRequestParams(final String key, final String value) {
        this(new HashMap<String, String>() {{
            put(key, value);
        }});
    }

    /**
     * Constructs a new RequestParams instance and populate it with multiple initial key/value
     * string param.
     *
     * @param keysAndValues a sequence of keys and values. Objects are automatically converted to
     *                      Strings (including the value {@code null}).
     * @throws IllegalArgumentException if the number of arguments isn't even.
     */
    public BaseRequestParams(Object... keysAndValues) {
        int len = keysAndValues.length;
        if (len % 2 != 0)
            throw new IllegalArgumentException("Supplied arguments must be even");
        for (int i = 0; i < len; i += 2) {
            String key = String.valueOf(keysAndValues[i]);
            String val = String.valueOf(keysAndValues[i + 1]);
            put(key, val);
        }
    }


    /**
     * Adds a key/value string pair to the request.
     *
     * @param key   the key name for the new param.
     * @param value the value string for the new param.
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }


    /**
     * Adds a key/value string pair to the request.
     *
     * @param key   the key name for the new param.
     * @param value the value string for the new param.
     */
    public void putHead(String key, String value) {
        if (key != null && value != null) {
            heads.put(key, value);
        }
    }


    public void putJsonParams(String params){
    	jsonParams = params;
    }


    /**
     * Adds a file to the request.
     *
     * @param key  the key name for the new param.
     * @param file the file to add.
     * @throws FileNotFoundException throws if wrong File argument was passed
     */
    public void put(String key, File file) throws FileNotFoundException {
        put(key, file, null, null);
    }

    /**
     * Adds a file to the request with custom provided file name
     *
     * @param key            the key name for the new param.
     * @param file           the file to add.
     * @param customFileName file name to use instead of real file name
     * @throws FileNotFoundException throws if wrong File argument was passed
     */
    public void put(String key, String customFileName, File file) throws FileNotFoundException {
        put(key, file, null, customFileName);
    }

    /**
     * Adds a file to the request with custom provided file content-type
     *
     * @param key         the key name for the new param.
     * @param file        the file to add.
     * @param contentType the content type of the file, eg. application/json
     * @throws FileNotFoundException throws if wrong File argument was passed
     */
    public void put(String key, File file, String contentType) throws FileNotFoundException {
        put(key, file, contentType, null);
    }

    /**
     * Adds a file to the request with both custom provided file content-type and file name
     *
     * @param key            the key name for the new param.
     * @param file           the file to add.
     * @param contentType    the content type of the file, eg. application/json
     * @param customFileName file name to use instead of real file name
     * @throws FileNotFoundException throws if wrong File argument was passed
     */
    public void put(String key, File file, String contentType, String customFileName) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        }
        if (key != null) {
            fileParams.put(key, new FileWrapper(file, contentType, customFileName));
        }
    }




    /**
     * Adds a int value to the request.
     *
     * @param key   the key name for the new param.
     * @param value the value int for the new param.
     */
    public void put(String key, int value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    public void clearUrlParams(){
    	urlParams.clear();
    }

    /**
     * Adds a long value to the request.
     *
     * @param key   the key name for the new param.
     * @param value the value long for the new param.
     */
    public void put(String key, long value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }


    /**
     * Removes a parameter from the request.
     *
     * @param key the key name for the parameter to remove.
     */
    public void remove(String key) {
        urlParams.remove(key);
        fileParams.remove(key);
    }

    /**
     * Check if a parameter is defined.
     *
     * @param key the key name for the parameter to check existence.
     * @return Boolean
     */
    public boolean has(String key) {
        return urlParams.get(key) != null ||
                fileParams.get(key) != null ;
    }


    public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public ConcurrentHashMap<String, String> getUrlParams() {
		return urlParams;
	}

	public ConcurrentHashMap<String, FileWrapper> getFileParams() {
		return fileParams;
	}

	public ConcurrentHashMap<String, String> getHeads() {
		return heads;
	}

	public String getJsonParams() {
		return jsonParams;
	}

	public void setJsonParams(String jsonParams) {
		this.jsonParams = jsonParams;
	}

    public void setJsonParams(boolean b){
        this.isJsonParams = b;
    }

	public boolean isRepeatable() {
		return isRepeatable;
	}

	public void setRepeatable(boolean isRepeatable) {
		this.isRepeatable = isRepeatable;
	}

	public boolean isShowDialog() {
		return isShowDialog;
	}

	public void setShowDialog(boolean isShowDialog) {
		this.isShowDialog = isShowDialog;
	}

	@Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }

        return result.toString();
    }


    public void setIsRepeatable(boolean flag) {
        this.isRepeatable = flag;
    }



    public static class FileWrapper implements Serializable {
        public final File file;
        public String contentType;
        public String customFileName;

        public FileWrapper(File file, String contentType, String customFileName) {
            this.file = file;
            this.contentType = contentType;
            this.customFileName = customFileName;
            if (TextUtils.isEmpty(contentType)) {
				this.contentType = "image/*";
			}
            if (TextUtils.isEmpty(customFileName)) {
				this.customFileName = file.getName();
			}
        }
    }

    public  RequestBody createRequestBody() {
        return  createRequestBody(this);
    }

    public  MultipartBody createFileRequestBody(){
        return  createFileRequestBody(this);
    }


        public static final   MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    public static final   MediaType FORM_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");

    /**
     * 请求参数
     * @param params
     * @return
     */
    public static RequestBody createRequestBody(BaseRequestParams params){
        NetLog.d("request", "params : " + params);
        if (TextUtils.isEmpty(params.jsonParams) && !params.isJsonParams) { // Form表单
            FormBody.Builder builder = new FormBody.Builder();
            // add 参数
            for (ConcurrentHashMap.Entry<String, String> entry : params.urlParams.entrySet()) {// 增加url params
                builder.add(entry.getKey(), entry.getValue());
            }


            return builder.build();
        }else { // body text
            JSONObject jsonObject = new JSONObject();

            if (!TextUtils.isEmpty(params.jsonParams)){
                try {
                    jsonObject = new JSONObject(params.jsonParams);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // add 参数
            for (ConcurrentHashMap.Entry<String, String> entry : params.urlParams.entrySet()) {// 增加url params
                try {
                    jsonObject.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            params.jsonParams = jsonObject.toString();

            return RequestBody.create(JSON_TYPE, params.jsonParams);
        }
    }
    /**
     * 文件上传
     * @param params
     * @return
     */
    public static MultipartBody createFileRequestBody(BaseRequestParams params){
        NetLog.d("request", "params:" + params);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //add 参数
        for (ConcurrentHashMap.Entry<String, String> entry : params.urlParams.entrySet()) {//增加url params
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : params.fileParams.entrySet()) {//增加url file
            builder.addFormDataPart(entry.getKey(), entry.getValue().customFileName,
                    RequestBody.create(MediaType.parse(entry.getValue().contentType), entry.getValue().file));
        }
        builder.setType(MultipartBody.FORM);

        MultipartBody requestBody = builder.build();
        return requestBody;
    }



}
