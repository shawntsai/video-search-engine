package videosearch;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Score {
	String videoName;
	double[] diffs;
	double score = 0;
	double ratio = 0;
	public Score(String videoName, double[] diffs, double ratio) {
		this.videoName = videoName;
		this.diffs = diffs;
		this.setScore();
		this.ratio = ratio;
	}
	// score is min diff
	public void setScore() {
		double sum = 0;
		for (double s: diffs) {
			sum += s;
		}
		this.score = -sum;
	}
	
	public double[] getDistribution() {
		double maxDiff = Double.MIN_VALUE;		
		for (double diff: diffs) maxDiff = Math.max(diff, maxDiff);
		double[] score = new double[diffs.length];
	
		for (int i = 0; i < score.length; i++) {
			score[i] = 1 - diffs[i] / maxDiff;
			
		}
		return score;
	}
	
}

public class Rank {
	PriorityQueue<Score> scores;
	String method;
	List<Video> videos;
	public Rank(List<Video> videos, String method) {
		this.videos = videos;
		this.method = method;
		scores = new PriorityQueue<Score> (new Comparator<Score>() {
			@Override
			public int compare(Score o1, Score o2) {
				if (o1.score == o2.score) return 0;
				else if (o1.score < o2.score) return 1;
				else return -1;
			}			
		});
	}			
	

	public void compare(Feature feature) {
		for (Video video: videos) {			
			Feature target = video.features.get(method);			
			Descriptor descriptor = new Descriptor(feature, target);
			
			Score score = new Score(video.name, descriptor.diffs, descriptor.ratio);
			scores.offer(score);
		}
	
	}
	


}
