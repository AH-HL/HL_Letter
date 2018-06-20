package com.aahl.hl_letter.config;

/**
 * @author : Mr.Hao
 * @date :  2018/6/7
 * @description : 所有用到 EventBus 的在这里配置，以10086开头 1000开始顺序排列 （以10086开头的作用当全局查找时不会和系统同名）
 */




public final class EventBusConfig {

  public static final int REQUEST_LOADING = 100861000; //网络请求对话框show
  public static final int REQUEST_LOADING_COLSE = 100861001; //网络请求对话框close

}
