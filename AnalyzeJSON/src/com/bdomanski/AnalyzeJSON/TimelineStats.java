package com.bdomanski.AnalyzeJSON;

import java.util.List;

public class TimelineStats {
	
	// Parsed JSON files for calculations
	private List<Location> timeline;
	private List<Query> firebase;
	
	private double percentSuccess;
	
	// Hits - Successful correlation
	// FarHits - Successful correlation, but there
	//			 was a nearer timeline entry
	// Misses - Unsuccessful correlation
	private int hits = 0;
	private int farHits = 0;
	private int misses = 0;
	
	public TimelineStats(List<Location> timeline_in, List<Query> firebase_in) {
		timeline = timeline_in;
		firebase = firebase_in;
		processData();
	}
	
	public double getSuccessRate() {
		return percentSuccess;
	}
	
	private void processData() {
		
		// Check for no previous correlations
		if(firebase.isEmpty()) {
			percentSuccess = 0;
		}
		
		int fbIndex = 0;
		Location l = new Location();
		Location prev = new Location();
		Query q = firebase.get(fbIndex);
		
		// Returns the place where there was a correlation with user and PlacesAPI
		Place p = q.getPlacesAPI().get(0);
		
		for(int i = 0; i < timeline.size(); ++i) {
			l = timeline.get(i);
			
			if(l.getTimestampMs() > q.getTime()) {
				
				if (l.getTimestampMs() - q.getTime() < q.getTime() - prev.getTimestampMs()) {
	
					// Check if a place is near a timeline location either
					// before or after it was recorded
					if(near(l, p)) ++hits;
					else if(near(prev, p)) ++farHits;
					else ++misses;
					
				} else {
					
					// Check if a place is near a timeline location either
					// before or after it was recorded
					if(near(prev, p)) ++hits;
					else if(near(l, p)) ++farHits;
					else ++misses;
				}
				
				if(firebase.size() > ++fbIndex) {
					// Increment to next query and place
					q = firebase.get(fbIndex);
					p = q.getPlacesAPI().get(0);
					
					// Decrement location index in case two places are
					// between two locations in time entries
					--i;
					
				} else {
					break;
				}
			}
			
			prev = l;
		}
		
		percentSuccess = (hits + farHits) / (double)firebase.size();
	}
	
	private Boolean near(Location l, Place p) {
		/* Latitude: 0.00015 degrees ~ 15 meters
		 * Longitude: 0.0002 degrees ~ 20 meters * cos(42 degrees),
		 * 			  = 15 meters
		 */
		return Math.abs(l.getLatitudeE7() - p.getLatitude()) < 0.00015 && 
			   Math.abs(l.getLongitudeE7() - p.getLongitude()) < 0.0002;
	}

}
