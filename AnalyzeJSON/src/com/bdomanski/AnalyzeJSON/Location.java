package com.bdomanski.AnalyzeJSON;

public class Location {
	private String timestampMs;
	private int latitudeE7;
	private int longitudeE7;
	
	public void setTimestampMs(String t) {
		timestampMs = t;
	}
	
	public String getTimestampMs() {
		return timestampMs;
	}
	
	public void setLatitudeE7(int l) {
		latitudeE7 = l;
	}
	
	public int getLatitudeE7() {
		return latitudeE7;
	}
	
	public void setLongitudeE7(int l) {
		longitudeE7 = l;
	}
	
	public int getLongitudeE7() {
		return longitudeE7;
	}
}
