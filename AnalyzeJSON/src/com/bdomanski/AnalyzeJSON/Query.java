package com.bdomanski.AnalyzeJSON;

import java.util.ArrayList;
import java.util.List;

public class Query {
	private long Time;
	private String UserInput;
	private List<Place> placesAPI = new ArrayList<Place>();
	
	public void setTime(long in) {
		Time = in;
	}
	
	public long getTime() {
		return Time;
	}
	
	public void setUserInput(String in) {
		UserInput = in;
	}
	
	public String getUserInput() {
		return UserInput;
	}
	
	public void setPlacesAPI(List<Place> in) {
		placesAPI = in;
	}
	
	public List<Place> getPlacesAPI() {
		return placesAPI;
	}
	
}
