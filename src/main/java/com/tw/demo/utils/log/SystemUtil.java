package com.tw.demo.utils.log;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang.ObjectUtils;

public class SystemUtil {
	public static final String PRODUCT_LINE = "house";

	private static String serverIp;
	
	static {
		try {
			serverIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			serverIp = "apartment mapi web unknown ip";
		}
	}
	
	public static String toStringWithoutException(Object obj){
		try{
			return ObjectUtils.toString(obj);
		}catch(Throwable ex){
			return "to string exception:"+ex.getMessage();
		}
	}
	
	  /**
	   * 获取服务器IP
	   * 
	   * @return 服务器IP
	   */
	  public static String getServerIp() {
		  return serverIp;
	  }
	  
	  /**
	   * 获取服务器名
	   * 
	   * @return 服务器名
	   */
	  public static String getServerName() {
	    try {
	      InetAddress addr = InetAddress.getLocalHost();
	      return addr.getHostName().toString();// 获得机器名
	    } catch (UnknownHostException e) {
	      return null;
	    }
	  }
}
