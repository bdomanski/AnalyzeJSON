package com.bdomanski.AnalyzeJSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Main {
	
	// Main interface to convert json to Java objects
	private static Gson gson1, gson2;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		gson1 = new Gson();
		gson2 = new Gson();
		
		List<Query> firebase = getFirebase(args[0]);
		List<Location> timeline = getTimeline(args[1]);
		
		// TODO: Implement algorithm to sort through firebase
		// 		 and timeline data
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
		return lh.getList();
	}
}






