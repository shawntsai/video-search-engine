package videosearch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class VideoSearch {
     
	static List<Video> videos = new ArrayList<>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
			// 
			String q = "first";
			
			Video queryVideo = new Video(q, queryFolder + "/" + q + "/" + q + ".wav", queryFolder + "/" + q);
			queryVideo.extractFeature();
			
			String method1 = "root mean square";
			Rank ranker = new Rank(videos, method1);
			ranker.compare(queryVideo.features.get(method1));
			while (ranker.scores.isEmpty() == false) {
				Score s = ranker.scores.poll();
				System.out.println(s.videoName);				
			}
			
			
			
//			String audioFilePath = "database_videos/flowers/flowers.wav";
			
//			FileInputStream audioStream = new FileInputStream("");
//			PlaySound playSound = new PlaySound(audioStream);
//			playSound.play();
//			Video v = new Video("flowers", audioFilePath);
//			v.extractFeature();
			

		
		} 
//		catch (FileNotFoundException e) {
//			System.out.println("can not find file");
//		}	
//		catch (PlayWaveException e) {
//			System.out.println("can not play");
//		}
		catch (Exception e) {
			System.out.println("something wrong");
		}
		
	}

}
