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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
	
	// Main interface to convert json to Java objects
	private static Gson gson1, gson2;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		gson1 = new Gson();
		gson2 = new Gson();
		
		// Read in data
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(new FileReader(args[0]));
		
		List<Query> queries = new ArrayList<Query>();
		
		if(element.isJsonObject()) {
			JsonArray users = element.getAsJsonObject().get("-KyHoEYhRo3802OIvzew").getAsJsonArray();
			int i = 0;
			for(JsonElement e : users) {
				queries.add(gson1.fromJson(e, Query.class));
				Query q = queries.get(i++);
				
				System.out.println(q.getUserInput() + '\n');
				
				for(Place p : q.getPlacesAPI()) {
					
					System.out.println(p.getLatitude());
					System.out.println(p.getLongitude());
					System.out.println(p.getLikelihood());
					System.out.println(p.getName());
					System.out.println(p.getTime());
				}
			}
		}
				
		// Read in data from Location History.json (Timeline data)
		LocationHistory lh = gson2.fromJson(new BufferedReader(new FileReader(args[1])), LocationHistory.class);
		List<Location> list = lh.getList();
		
		for(Location loc : list) {
			System.out.println(loc.getTimestampMs());
			System.out.println(loc.getLatitudeE7());
			System.out.println(loc.getLongitudeE7());
		}
	}
}






