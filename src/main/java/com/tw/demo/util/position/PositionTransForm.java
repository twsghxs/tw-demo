package com.tw.demo.util.position;

public class PositionTransForm {

	private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;  
	  
	/** 
	 * 中国正常坐标系GCJ02协议的坐标，转到 百度地图对应的 BD09 协议坐标 
	 *  
	 * @param lat 
	 * @param lng 
	 */  
	public static PositionObject Convert_GCJ02_To_BD09(PositionObject gcj02Object) {  
	    double x = gcj02Object.getLon(), y = gcj02Object.getLat();  
	    double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);  
	    double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);  
	    return new PositionObject(z * Math.sin(theta) + 0.006, z * Math.cos(theta) + 0.0065);
	}  
	  
	public static double Convert_GCJ02_To_BD09_Lat(double lat, double lng) {  
	    double x = lng, y = lat;  
	    double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);  
	    double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);  
	    lng = z * Math.cos(theta) + 0.0065;  
	    lat = z * Math.sin(theta) + 0.006;  
	    return lat;  
	}  
	  
	public static double Convert_GCJ02_To_BD09_Lng(double lat, double lng) {  
	    double x = lng, y = lat;  
	    double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);  
	    double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);  
	    lng = z * Math.cos(theta) + 0.0065;  
	    lat = z * Math.sin(theta) + 0.006;  
	    return lng;  
	}  
	  
	/** 
	 * 百度地图对应的 BD09 协议坐标，转到 中国正常坐标系GCJ02协议的坐标 
	 *  
	 * @param lat 
	 * @param lng 
	 */  
	public static PositionObject Convert_BD09_To_GCJ02(PositionObject bd09Object) {  
	    double x = bd09Object.getLon() - 0.0065, y = bd09Object.getLat() - 0.006;  
	    double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
	    double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);   
	    return new PositionObject(z * Math.sin(theta), z * Math.cos(theta));
	}  
}
