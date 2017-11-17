package com.bdomanski.AnalyzeJSON;

import java.util.ArrayList;
import java.util.List;

public class LocationHistory {
	private List<Location> locations = new ArrayList<Location>();
	
	public void setList(List<Location> l) {
		locations = l;
	}
	
	public List<Location> getList() {
		return locations;
	}
}
