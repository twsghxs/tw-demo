package com.tw.demo.utils.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionLogEntity {

	  private boolean flag = false;
	  // id
	  private int id;
	  // 唯一id
	  private String uniqueID;
	  // 用户id
	  private long userID;
	  // sessionid
	  private String sessionID;
	  // contextid
	  private String contextID;
	  // executeid
	  private String executeID;
	  // 是否异常，异常=1，正常=0
	  private int logType;
	  // 服务名称
	  private String serviceName;
	  // 方法名称
	  private String methodName;
	  //
	  private String fromUrl;
	  //
	  private String referUrl;
	  //
	  private String fromSystem;
	  //
	  private String clientIP;
	  //
	  private String protocolServerIP;
	  // 服务器名称
	  private String appServerIP;
	  // 异常信息
	  private String exceptionMessage;
	  // 详细异常信息
	  private String detail;
	  // 执行时间
	  private float excuteTimeSpan;
	  // 日志时间
	  private Date logTime;
	  //
	  private String extend1;
	  //
	  private String extend2;
	  //
	  private Date createTime;
	  // 产品线
	  private String productLine;
	  // 业务状况返回值
	  private int responseCode;

	  public boolean isFlag() {
	    return flag;
	  }

	  public void setFlag(boolean flag) {
	    this.flag = flag;
	  }

	  public int getId() {
	    return id;
	  }

	  public void setId(int id) {
	    this.id = id;
	  }

	  public String getUniqueID() {
	    return uniqueID;
	  }

	  public void setUniqueID(String uniqueID) {
	    this.uniqueID = uniqueID;
	  }

	  public long getUserID() {
	    return userID;
	  }

	  public void setUserID(long userID) {
	    this.userID = userID;
	  }

	  public String getSessionID() {
	    return sessionID;
	  }

	  public void setSessionID(String sessionID) {
	    this.sessionID = sessionID;
	  }

	  public String getContextID() {
	    return contextID;
	  }

	  public void setContextID(String contextID) {
	    this.contextID = contextID;
	  }

	  public String getExecuteID() {
	    return executeID;
	  }

	  public void setExecuteID(String executeID) {
	    this.executeID = executeID;
	  }

	  public int getLogType() {
	    return logType;
	  }

	  public void setLogType(int logType) {
	    this.logType = logType;
	  }

	  public String getServiceName() {
	    return serviceName;
	  }

	  public void setServiceName(String serviceName) {
	    this.serviceName = serviceName;
	  }

	  public String getMethodName() {
	    return methodName;
	  }

	  public void setMethodName(String methodName) {
	    this.methodName = methodName;
	  }

	  public String getFromUrl() {
	    return fromUrl;
	  }

	  public void setFromUrl(String fromUrl) {
	    this.fromUrl = fromUrl;
	  }

	  public String getReferUrl() {
	    return referUrl;
	  }

	  public void setReferUrl(String referUrl) {
	    this.referUrl = referUrl;
	  }

	  public String getFromSystem() {
	    return fromSystem;
	  }

	  public void setFromSystem(String fromSystem) {
	    this.fromSystem = fromSystem;
	  }

	  public String getClientIP() {
	    return clientIP;
	  }

	  public void setClientIP(String clientIP) {
	    this.clientIP = clientIP;
	  }

	  public String getProtocolServerIP() {
	    return protocolServerIP;
	  }

	  public void setProtocolServerIP(String protocolServerIP) {
	    this.protocolServerIP = protocolServerIP;
	  }

	  public String getAppServerIP() {
	    return appServerIP;
	  }

	  public void setAppServerIP(String appServerIP) {
	    this.appServerIP = appServerIP;
	  }

	  public String getExceptionMessage() {
	    return exceptionMessage;
	  }

	  public void setExceptionMessage(String exceptionMessage) {
	    this.exceptionMessage = exceptionMessage;
	  }

	  public String getDetail() {
	    return detail;
	  }

	  public void setDetail(String detail) {
	    this.detail = detail;
	  }

	  public float getExcuteTimeSpan() {
	    return excuteTimeSpan;
	  }

	  public void setExcuteTimeSpan(float excuteTimeSpan) {
	    this.excuteTimeSpan = excuteTimeSpan;
	  }

	  public Date getLogTime() {
	    return logTime;
	  }

	  public void setLogTime(Date logTime) {
	    this.logTime = logTime;
	  }

	  public String getExtend1() {
	    return extend1;
	  }

	  public void setExtend1(String extend1) {
	    this.extend1 = extend1;
	  }

	  public String getExtend2() {
	    return extend2;
	  }

	  public void setExtend2(String extend2) {
	    this.extend2 = extend2;
	  }

	  public Date getCreateTime() {
	    return createTime;
	  }

	  public void setCreateTime(Date createTime) {
	    this.createTime = createTime;
	  }

	  public String getProductLine() {
	    return productLine;
	  }

	  public void setProductLine(String productLine) {
	    this.productLine = productLine;
	  }

	  public int getResponseCode() {
	    return responseCode;
	  }

	  public void setResponseCode(int responseCode) {
	    this.responseCode = responseCode;
	  }

	  public ActionLogEntity() {
		  logType = 0;
		  appServerIP = SystemUtil.getServerIp();
	  }

	  @Override
	  public String toString() {
	    String fieldSplitChar = "\t";
	    StringBuilder ret = new StringBuilder();
	    // String ret = "";
	    try {
	      ret.append(String.valueOf(id = 0) + fieldSplitChar);
	      ret.append((uniqueID == null ? "null" : uniqueID) + fieldSplitChar);
	      ret.append(String.valueOf(userID = 0) + fieldSplitChar);
	      ret.append((sessionID == null ? "null" : sessionID) + fieldSplitChar);
	      ret.append((contextID == null ? "null" : contextID) + fieldSplitChar);
	      ret.append((executeID == null ? "null" : executeID) + fieldSplitChar);
	      ret.append(String.valueOf(logType) + fieldSplitChar);
	      ret.append((serviceName == null ? "null" : serviceName) + fieldSplitChar);
	      ret.append((methodName == null ? "null" : methodName) + fieldSplitChar);
	      ret.append((fromUrl == null ? "null" : fromUrl) + fieldSplitChar);
	      ret.append((referUrl == null ? "null" : referUrl) + fieldSplitChar);
	      ret.append((fromSystem == null ? "null" : fromSystem) + fieldSplitChar);
	      ret.append((clientIP == null ? "null" : clientIP) + fieldSplitChar);
	      ret.append((protocolServerIP == null ? "null" : protocolServerIP) + fieldSplitChar);
	      ret.append((appServerIP == null ? "null" : appServerIP) + fieldSplitChar);
	      ret.append((exceptionMessage == null ? "null" : exceptionMessage.replace("\t", ",")
	          .replace("\r", ",").replace("\n", ","))
	          + fieldSplitChar);
	      ret.append((detail == null ? "null" : detail.replace("\t", ",").replace("\r", ",")
	          .replace("\n", ","))
	          + fieldSplitChar);
	      ret.append(String.valueOf(excuteTimeSpan) + fieldSplitChar);
	      ret.append((logTime == null
	          ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
	          : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(logTime)) + fieldSplitChar);
	      ret.append((extend1 == null ? "null" : extend1) + fieldSplitChar);
	      ret.append((extend2 == null ? "null" : extend2) + fieldSplitChar);
	      ret.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + fieldSplitChar);
	      ret.append((productLine == null ? "null" : productLine) + fieldSplitChar);
	      ret.append(String.valueOf(responseCode));
	    } catch (Exception e) {
	      // TODO: handle exception
	      System.out.println("ActionLogEntity.toString() exception, the info : " + e.getMessage());
	    }
	    return ret.toString().replace("\r", "").replace("\n", "");
	  }
	}
