package com.aahl.hl_letter.jsbridge;

/**
 * @author : Mr.Hao
 * @date :  2018/5/10
 * @description :
 */

public interface VpJsBridge {
	
	public void send(String data);
	public void send(String data, CallBackFunction responseCallback);
	
	

}
