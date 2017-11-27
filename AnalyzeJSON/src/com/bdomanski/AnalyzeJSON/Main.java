package com.bdomanski.AnalyzeJSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Main {
	
	// Main interface to convert json to Java objects
	private static Gson gson1, gson2;
	
	// Returns results for correlating Firebase data
	private static FirebaseStats fbstats;
	
	// Returns results for correlating Timeline data
	private static TimelineStats tlstats;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		gson1 = new Gson();
		gson2 = new Gson();
		
		List<Query> results = new ArrayList<Query>();
		List<Query> firebase = getFirebase(args[0]);
		List<Location> timeline = getTimeline(args[1]);
		
		fbstats = new FirebaseStats(firebase);
		results = fbstats.getResults();
		
		// Only compare previous results to timeline data to
		// avoid double comparisons with PlacesAPI
		tlstats = new TimelineStats(timeline, results);
		
		// Print out results from correlating data
		System.out.print("Correlation rate between user input and Places API: ");
		System.out.println(fbstats.getSuccessRate());
		
		System.out.print("Correlation rate between past results and timeline: ");
		System.out.println(tlstats.getSuccessRate());
		
	}
	
	private static List<Query> getFirebase(String file) throws FileNotFoundException {
		List<Query> queries = new ArrayList<Query>();
		
		// Read in data
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(new FileReader(file));
		
		if(element.isJsonObject()) {
			JsonArray users = element.getAsJsonObject().get("-KyHoEYhRo3802OIvzew").getAsJsonArray();
			for(JsonElement e : users) {
				queries.add(gson1.fromJson(e, Query.class));
			}
		}
		
		return queries;
	}
	
	private static List<Location> getTimeline(String file) throws FileNotFoundException {
		LocationHistory lh = gson2.fromJson(new BufferedReader(new FileReader(file)), LocationHistory.class);
		List<Location> list = lh.getList();
		
		// Order should be first entry -> last entry
		Collections.reverse(list);
		
		return list;
	}
}






