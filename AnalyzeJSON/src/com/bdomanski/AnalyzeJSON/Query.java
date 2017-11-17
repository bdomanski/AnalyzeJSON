package com.bdomanski.AnalyzeJSON;

import java.util.ArrayList;
import java.util.List;

public class Query {
	private String userInput;
	private List<Place> placesAPI = new ArrayList<Place>();
	
	public void setUserInput(String in) {
		userInput = in;
	}
	
	public String getUserInput() {
		return userInput;
	}
	
	public void setPlacesAPI(List<Place> in) {
		placesAPI = in;
	}
	
	public List<Place> getPlacesAPI() {
		return placesAPI;
	}
	
}
