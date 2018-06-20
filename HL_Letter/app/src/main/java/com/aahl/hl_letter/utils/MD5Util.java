package com.aahl.hl_letter.utils;

import com.aahl.sdk_rxretrofit.utils.VPLog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author : Mr.Hao
 * @date :  2018/6/19
 * @description : 功能简介：MD5加密工具类 （密码等安全信息存入数据库时，转换成MD5加密形式）
 */

public class MD5Util {

	/**
	 * MD5 --- 16位加密
	 */
	  public static String MD516(String sourceStr) {
	        String result = "";
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(sourceStr.getBytes());
	            byte b[] = md.digest();
	            int i;
	            StringBuffer buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	            result = buf.toString();
	            System.out.println("MD5(" + sourceStr + ",32) = " + result);
	            System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
	            
	            return buf.toString().substring(8, 24).toUpperCase();
	        } catch (NoSuchAlgorithmException e) {
	            System.out.println(e);
	        }
	        return result;
	    }

	/**
	 * MD5 --- 32位加密
	 */
	  public static String MD532(String sourceStr) {
	        String result = "";
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(sourceStr.getBytes());
	            byte b[] = md.digest();
	            int i;
	            StringBuffer buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	            result = buf.toString();
	            VPLog.d("tag","MD5(" + sourceStr + ") = " + result);
	            return result.toUpperCase();
	        } catch (NoSuchAlgorithmException e) {
	            System.out.println(e);
	        }
	        return result;
	    } 
}