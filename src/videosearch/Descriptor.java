package videosearch;

import java.util.List;
import java.util.PriorityQueue;




// 5 sec audo query
// 20 sec lib audio
public class Descriptor {
	
//	Video video;
//	double[] featureScore;
	double[] diffs;
	double ratio;
	
	public Descriptor(Feature feature, Feature target) {
//		System.out.println("method ---- --------------");
//		System.out.println(feature.method);
		if (feature.method.equals("rgb color")) {
			ColorFeature cfeature = (ColorFeature) feature;
			ColorFeature ctarget = (ColorFeature) target;
			
			diffs = setDifferences(cfeature.data, ctarget.data);
			for (double diff: diffs) {
				System.out.print(diff + " ");
			}
			
		} else {
			diffs = setDifferences(listToArray(feature.data), listToArray(target.data));
		}
//		
	}
	
	private double[] setDifferences(List<int[]> query, List<int[]> target) {
		System.out.println(query.size());
		System.out.println(target.size());
		double[] diffs = new double[query.size()];
		double minHueDiff = Double.MAX_VALUE;
		int st = 0;
		for (int i = 0; i <= target.size() - query.size(); i++) {
			double hueDiff = 0;
			for (int j = 0; j < query.size(); j++) {				
				hueDiff += getHueMaxDiff(query.get(j), target.get(i + j));		
			}
			if (hueDiff < minHueDiff) {
				minHueDiff = hueDiff;
				st = i;
			}
		}
		
		for (int i = 0; i < diffs.length; i++) {
			diffs[i] = getHueMaxDiff(query.get(i), target.get(i + st));			
		
		}
		
		setStartRatio(st, target.size());
		return diffs;
	}
	private double getHueMaxDiff(int[] is, int[] is2) {
		double diff = 0;
		for (int i = 0; i < is.length; i++) {
			diff = Math.max(diff, Math.abs(is[i] - is2[i]));
			
		}
		return diff;
	}

	

	private double getHueDiff(int[] is, int[] is2) {
		double diff = 0;
		for (int i = 0; i < is.length; i++) {
			diff += Math.abs(is[i] - is2[i]);
			
		}
//		System.out.println("hue diff");
//		System.out.println(Math.log(diff));
		return diff;
	}

	// return scores when we scroll over target video in library
	public double[] setDifferences(double[] query, double[] target) {
		double[] diffs = new double[query.length];
		
		int st = 0; // should be the start frame
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
		System.out.println("total is ");
		System.out.println(target.length);
		System.out.println("start frame is here");
		System.out.println(st);
		setStartRatio(st, target.length);
		return diffs;
	}
	
	void setStartRatio(int st, int total) {
		this.ratio = st * 1. / total;
	}
	
	double[] listToArray(List<Double> data) {
		double[] result = new double[data.size()];
		for (int i = 0; i < data.size(); i++) {
			result[i] = data.get(i);
		}
		return result;
	}
	

	
	private double absoluteDistance(double[] array1, double[] array2) {
		double difference = 0;
		for (int i = 0; i < array1.length; i++) {
			difference += Math.abs(array1[i] - array2[i]);
		}
		return difference / array1.length;
	}
	
}
