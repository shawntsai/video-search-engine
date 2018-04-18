package videosearch;

import java.util.List;
import java.util.PriorityQueue;

class Score {
	String videoName;
	double[] scores;
	double maxScore = 0;
	public Score(String videoName, double[] scores) {
		this.videoName = videoName;
		this.scores = scores;
		this.setMaxScore();
	}
	public void setMaxScore() {
		double max = Integer.MAX_VALUE;
		for (double s: scores) {
			if (max < s) max = s;
		}
		this.maxScore = max;
	}
}

public class Rank {
	PriorityQueue<Score> scores;
	String method;
	List<Video> videos;
	public Rank(List<Video> videos, String method) {
		this.videos = videos;
		this.method = method;
		this.scores = new PriorityQueue<>((a, b) -> (int) (b.maxScore - a.maxScore));
	}			

	private void compare(double[] query) {
		Descriptor descirptor = new Descriptor();
		for (Video video: videos) {
			double[] target = listToArray(video.features.get(method));
			Score score = new Score(video.name, descirptor.getScores(query, target));
			scores.offer(score);
		}
	
	}
	
	public void compare(List<Double> query) {
		compare(listToArray(query));
	}

	double[] listToArray(List<Double> data) {
		double[] result = new double[data.size()];
		for (int i = 0; i < data.size(); i++) {
			result[i] = data.get(i);
		}
		return result;
	}
}
