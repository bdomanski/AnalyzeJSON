package com.bdomanski.AnalyzeJSON;

public class Location {
	private String timestampMs;
	private int latitudeE7;
	private int longitudeE7;
	
	public Location() {
		timestampMs = "0";
		latitudeE7 = 0;
		longitudeE7 = 0;
	}
	
	public void setTimestampMs(String t) {
		timestampMs = t;
	}
	
	public long getTimestampMs() {
		return Long.parseLong(timestampMs);
	}
	
	public void setLatitudeE7(int l) {
		latitudeE7 = l;
	}
	
	public double getLatitudeE7() {
		return latitudeE7 / 10000000.0;
	}
	
	public void setLongitudeE7(int l) {
		longitudeE7 = l;
	}
	
	public double getLongitudeE7() {
		return longitudeE7 / 10000000.0;
	}
}
