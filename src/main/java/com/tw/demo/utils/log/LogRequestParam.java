package com.tw.demo.utils.log;

import java.util.concurrent.ConcurrentHashMap;


public class LogRequestParam {
	private static ConcurrentHashMap map = new ConcurrentHashMap();

	public static void setContent(Object content) {
		map.put(Thread.currentThread().getId(), content);
	}
	
	public static Object getContent(){
	  if (map.containsKey(Thread.currentThread().getId())) {
	    return map.get(Thread.currentThread().getId());
	  }
	  return null;
	}
}
