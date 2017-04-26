package com.tw.demo.utils.http;

import com.alibaba.fastjson.JSON;

/**
 * 自定义HTTP状态
 * 
 * @author bing.cheng
 *
 */
public enum HttpResultStatus {
	INIT(0, "初始状态"), 
	SUCCESS(200, "正常"), 
	STATUS_CODE_CONNERR(601, "HTTP连接异常"), 
	STATUS_CODE_TIMEOUT(604, "IO读超时"), 
	STATUS_CODE_OTHER(605, "其它错误"), 
	UNDEFINE(999, "未定义异常");

	private Integer code;
	private String message;

	HttpResultStatus(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}

