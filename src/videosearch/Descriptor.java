package videosearch;

import java.util.List;
import java.util.PriorityQueue;




// 5 sec audo query
// 20 sec lib audio
public class Descriptor {
	
//	Video video;
//	double[] featureScore;
	
	public Descriptor() {
	}
	
	// return scores when we scroll over target video in library
	public double[] getScores(double[] query, double[] target) {
		double[] scores = new double[target.length - query.length + 1];
		int l;
		
		for (int i = 0; i <= target.length - query.length; i++) {
			for (l = 0; l < query.length; l++) {
				scores[i] += Math.abs(query[l] - target[i + l]);
			}
		}
		
		for (int i = 0; i < scores.length; i++) {
			scores[i] = 1 - scores[i] / query.length;
		}
		return scores;
	}
	
	private double absoluteDistance(double[] array1, double[] array2) {
		double difference = 0;
		for (int i = 0; i < array1.length; i++) {
			difference += Math.abs(array1[i] - array2[i]);
		}
		return difference / array1.length;
	}
	
}
