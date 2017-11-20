package com.bdomanski.AnalyzeJSON;

import java.util.List;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class FirebaseStats {
	
	// Stores the main data for calculations
	private List<Query> firebase;
	
	// How often the user and PlacesAPI was similar
	private double percentSuccess;

	/* 
	 * Used for calculating how similar two strings are
	 * 
	 * The Levenshtein distance of two string is equal to
	 * the amount of changes (swaps, insertions, deletions)
	 * to transform one string into another.
	 */
	private static LevenshteinDistance stringComp = new LevenshteinDistance();
	
	/*
	 *  Threshold for percent difference in UserInput 
	 *  and PlacesAPI name of restaurant results
	 */
	private static final double THRESHOLD = 0.3; 
	
	public FirebaseStats(List<Query> firebase_in) {
		firebase = firebase_in;
		processData();
	}
	
	public double getSuccessRate() {
		return percentSuccess;
	}
	
	private void processData() {
		int hits = 0;
		int size = 0;
		int compResult;
		String user, placesAPI;
		
		for(Query q : firebase) {
			if(q != null) {
				++size;
				user = q.getUserInput();
				placesAPI = q.getPlacesAPI().get(0).getName();
				compResult = stringComp.apply(placesAPI, user);
				
				if(compResult < Math.max(placesAPI.length(), user.length()) * THRESHOLD) ++hits;
			}
		}
		
		percentSuccess = hits / (double)size;
	}
}
