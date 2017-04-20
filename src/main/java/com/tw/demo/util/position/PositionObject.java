package com.tw.demo.util.position;

public class PositionObject {

	private Double lat;
	
	private Double lon;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public PositionObject(Double lat, Double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}
	
}
