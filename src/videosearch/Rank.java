package videosearch;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


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
//			System.out.println("compare");
//			for (double diff: descriptor.diffs) {
//				System.out.print(diff + " ");
//			}
			Score score = new Score(video.name, descriptor.diffs, descriptor.ratio);
			scores.offer(score);
		}
	}

	public PriorityQueue<Score> getScores() {
		return this.scores;
	}

}
