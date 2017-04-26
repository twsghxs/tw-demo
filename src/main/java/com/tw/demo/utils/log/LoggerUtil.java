package com.tw.demo.utils.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

	private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
	
	public static void addCheckListLog(long startTime, String serviceName,
			String methodName) {
		ActionLogEntity realActionLogEntity = new ActionLogEntity();
		realActionLogEntity.setExcuteTimeSpan(System.currentTimeMillis()
				- startTime);
		realActionLogEntity.setLogTime(Calendar.getInstance().getTime());
		realActionLogEntity.setCreateTime(Calendar.getInstance().getTime());

		realActionLogEntity.setServiceName(serviceName);
		realActionLogEntity.setMethodName(methodName);

		realActionLogEntity.setProductLine(SystemUtil.PRODUCT_LINE);
		realActionLogEntity.setResponseCode(0);
		
		
		if (LogRequestParam.getContent() != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("requestParam:");
			try {
			  sb.append(LogRequestParam.getContent()).append(";");
            } catch (Exception e) {
              // TODO: handle exception
            }
			realActionLogEntity.setDetail(sb.toString());
		}
		
		LoggerUtil.actionLogEntityInfo(realActionLogEntity.toString());
	}
	
	public static void addCheckListLog(long startTime, String serviceName,
	                                   String methodName, String input) {
	  ActionLogEntity realActionLogEntity = new ActionLogEntity();
	  realActionLogEntity.setExcuteTimeSpan(System.currentTimeMillis()
	    - startTime);
	  realActionLogEntity.setLogTime(Calendar.getInstance().getTime());
	  realActionLogEntity.setCreateTime(Calendar.getInstance().getTime());
	  
	  realActionLogEntity.setServiceName(serviceName);
	  realActionLogEntity.setMethodName(methodName);
	  
		realActionLogEntity.setProductLine(SystemUtil.PRODUCT_LINE);
	  realActionLogEntity.setResponseCode(0);
	  
	  
	  if (LogRequestParam.getContent() != null) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("requestParam:")
	    .append(input).append(";");
	    realActionLogEntity.setDetail(sb.toString());
	  }
	  
	  LoggerUtil.actionLogEntityInfo(realActionLogEntity.toString());
	}

	public static void addCheckListLog(long startTime, String serviceName,
			String methodName, Throwable ex) {

		ActionLogEntity realActionLogEntity = new ActionLogEntity();
		realActionLogEntity.setExcuteTimeSpan(System.currentTimeMillis()
				- startTime);
		realActionLogEntity.setLogTime(Calendar.getInstance().getTime());
		realActionLogEntity.setCreateTime(Calendar.getInstance().getTime());
		realActionLogEntity.setLogType(1);
		realActionLogEntity.setResponseCode(-1);
		realActionLogEntity.setProductLine(SystemUtil.PRODUCT_LINE);
		realActionLogEntity.setServiceName(serviceName);
		realActionLogEntity.setMethodName(methodName);
		if (ex != null) {
			realActionLogEntity.setExceptionMessage(ex.getMessage());
			StringBuilder sb = new StringBuilder();

			if(LogRequestParam.getContent()!=null){
				sb.append("requestParam:")
					.append(LogRequestParam.getContent())
					.append(";");
			}
			
			if (ex.getCause() != null) {
				sb.append(ex.getCause().getMessage());
			}
			String exceptionString=  null;
			if (ex != null) {
				StringWriter sw=null;
				PrintWriter pw=null;
				try{
					sw = new StringWriter();
					pw = new PrintWriter(sw);
					ex.printStackTrace(pw);
					exceptionString=sw.toString();
					exceptionString = exceptionString.replaceAll("\n", "#");
				}catch(Throwable t){
					logger.error("error", t);
				}finally{
					if (pw != null) {
						try {
							pw.close();
						} catch (Throwable e) {
							logger.error("error", e);
						}
					}
					if (sw != null) {
						try {
							sw.close();
						} catch (Throwable e) {
							logger.error("error", e);
						}
					}
				}
			}
			sb.append(exceptionString);
			realActionLogEntity.setDetail(sb.toString());
		}
		LoggerUtil.actionLogEntityInfo(realActionLogEntity.toString());
	}
	
	public static void actionLogEntityInfo(String message) {
		LoggerFactory.getLogger("actionLogEntityLogger").info(message);
	}

	
	public static void searchListLogInfo(String message) {
		LoggerFactory.getLogger("searchListLogger").info(message);
	}
}
