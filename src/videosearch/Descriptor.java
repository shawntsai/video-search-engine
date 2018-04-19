package videosearch;

import java.util.List;
import java.util.PriorityQueue;




// 5 sec audo query
// 20 sec lib audio
public class Descriptor {
	
//	Video video;
//	double[] featureScore;
	double[] diffs;
	public Descriptor(double[] query, double[] target) {
		diffs = setDifferences(query, target);
	}
	
	// return scores when we scroll over target video in library
	public double[] setDifferences(double[] query, double[] target) {
		double[] diffs = new double[query.length];
		
		
		int st = 0;
		double minDiff = Double.MAX_VALUE;
		for (int i = 0; i <= target.length - query.length; i++) {			
			int diff = 0;
			for (int l = 0; l < query.length; l++) {
				diff += Math.abs(query[l] - target[i + l]);				
			}
			if (diff < minDiff) {
				minDiff = diff;
				st = i;
			}
		}		
		
		for (int i = 0; i < diffs.length; i++) {
			diffs[i] = Math.abs(target[i + st] - query[i]);
		}
		return diffs;
	}
	

	
	private double absoluteDistance(double[] array1, double[] array2) {
		double difference = 0;
		for (int i = 0; i < array1.length; i++) {
			difference += Math.abs(array1[i] - array2[i]);
		}
		return difference / array1.length;
	}
	
}
