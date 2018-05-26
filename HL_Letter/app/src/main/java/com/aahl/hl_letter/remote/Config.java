/*
 * @FileName: Config.java
 * @Author
 * @Description:
 * @Date 2012-11-26 下午2:22:43
 * @CopyRight ZTE Corporation
 */
package com.aahl.hl_letter.remote;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final static String DEFAULT_PROPERTY_PATH = "/Config.properties";
    private static Config       instance              = null;
    private static Properties mPprops               = null;


    public Config(){
        instance = this;
        mPprops = new Properties();
        try{
            InputStream in = Config.class.getResourceAsStream(DEFAULT_PROPERTY_PATH);
            mPprops.load(in);
            in.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public static Config getInstance(){
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }


    public String getProp(String prop){
        return mPprops.getProperty(prop);
    }
}
