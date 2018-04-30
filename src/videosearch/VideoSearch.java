package videosearch;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

public class VideoSearch {
     
	static List<Video> videos = new ArrayList<>();
	
	
	public static void main(String[] args) {		
		try {
			
			String queryFolder = "query";
			String dbName = "database_videos";
			File databaseFolder = new File(dbName);
			for (File fileOrFolder: databaseFolder.listFiles()) {
				if (fileOrFolder.isDirectory()) {
					String q = fileOrFolder.getName();
					String audioFilePath = dbName + "/" + q + "/" + q + ".wav";
					videos.add(new Video(q, audioFilePath, dbName + "/" + q));					
				}
			}
			for (Video video: videos) {
				video.extractFeature();
			}
			String q = "first";
			
			Video queryVideo = new Video(q, queryFolder + "/" + q + "/" + q + ".wav", queryFolder + "/" + q);
			queryVideo.extractFeature();
			
			for (String method: queryVideo.features.keySet()) {
//				if (! method.equals("rgb image motion") || ! method.equals("rgb color")) continue;
				System.out.println("rank this");
				System.out.println(method);
				Rank ranker = new Rank(videos, method);
				ranker.compare(queryVideo.features.get(method));
				int i = 1;
				while (ranker.scores.isEmpty() == false) {
					Score s = ranker.scores.poll();
//					System.out.println(i++);
//					System.out.println(s.videoName);			
//					System.out.println(s.score);
//					System.out.println(s.ratio);
//					System.out.println("start frame is ");
//					System.out.println((int) (600 * s.ratio));
					for (double t: s.getDistribution()) {
						System.out.print(t);
						System.out.print(" ");
					}
				}
			}
			
//			String method1 = "root mean square";
//			Rank ranker = new Rank(videos, method1);
//			ranker.compare(queryVideo.features.get(method1));
//			while (ranker.scores.isEmpty() == false) {
//				Score s = ranker.scores.poll();
//				System.out.println(s.videoName);			
//				System.out.println(s.score);
//				for (double v : s.getDistribution()) {
//					System.out.print(v);
//					System.out.print(" ");
//				}
//				break;
//			}
//			
			
			
//			String audioFilePath = "database_videos/flowers/flowers.wav";
			
//			FileInputStream audioStream = new FileInputStream("");
//			PlaySound playSound = new PlaySound(audioStream);
//			playSound.play();

		
		} 
//		catch (FileNotFoundException e) {
//			System.out.println("can not find file");
//		}	
//		catch (PlayWaveException e) {
//			System.out.println("can not play");
//		}
		catch (Exception e) {
			System.out.println("something wrong");
			e.printStackTrace();
		}
		
	}

}
