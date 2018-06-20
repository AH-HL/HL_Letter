package com.aahl.hl_letter.remote;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.aahl.hl_letter.base.BaseApplication;
import com.aahl.sdk_rxretrofit.bean.BaseBean;

import java.util.Collection;

/**
 * @author : Mr.Hao
 * @date :  2018/6/19
 * @description :
 */

public class HttpResultBean<T> extends BaseBean {
	public int returnCode ;
	public String message ="网络异常!";
	public T records;
	public T dataInfo;
	public T pageData;

	public int TotalCount;
    public int PageIndex;
    public int PageSize;
    public int TotalPage;
    public int current;
    public int offsetCurrent;
    public int pages;
    public boolean searchCount;
    public boolean asc;
    public boolean optimizeCount;
    public int total;
	
	public boolean isEmpty(Object records) {
		if (records == null) {
			return true;
		}

		if (records instanceof Collection) {
			Collection s = (Collection) records;
			return s.size() == 0;
		}

		if (records instanceof String) {
			String s = (String) records;
			return TextUtils.isEmpty(s);
		}

		return false;
	}

	public boolean isSuccess() {
		if (returnCode == 1000) {
			return true;
		}
		return false;
	}
	
	public void showErrorMsg(){
		 showErrorMsg(BaseApplication.getInstance());
	}
	
	public void showErrorMsg(Context cxt){
		if (cxt == null) {
			return ;
		}
		try {
			new Exception().printStackTrace();
			Toast.makeText(BaseApplication.getInstance(), ""+message, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
