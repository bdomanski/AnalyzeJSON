package com.bdomanski.AnalyzeJSON;

public class Place {
	private double Latitude;
	private double Longitude;
	private double Likelihood;
	private int Time;
	private String Name;
	
	
	public void setLatitude(int in) {
		Latitude = in;
	}
	
	public double getLatitude() {
		return Latitude;
	}
	
	public void setLongitude(double in) {
		Longitude = in;
	}
	
	public double getLongitude() {
		return Longitude;
	}
	
	public void setLikelihood(double in) {
		Likelihood = in;
	}
	
	public double getLikelihood() {
		return Likelihood;
	}
	
	public void setTime(int in) {
		Time = in;
	}
	
	public int getTime() {
		return Time;
	}
	
	public void setName(String in) {
		Name = in;
	}
	
	public String getName() {
		return Name;
	}
}

