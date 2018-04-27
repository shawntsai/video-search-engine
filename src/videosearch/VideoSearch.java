package videosearch;
import ui.VideoSearchUI;
import ui.model.AllResultModel;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoSearch {
     
	static List<Video> videos = new ArrayList<>();
	private final static String queryFolder = "query";
	private final static String dbName = "database_videos";
	public Video queryVideo;
	private AllResultModel storedResult = new AllResultModel();

	public void loadDataBase() {
	    try {
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
        }catch (Exception e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
    }

    public void loadQuery(String q) {
		try {
			this.queryVideo = new Video(q, queryFolder + "/" + q + "/" + q + ".wav", queryFolder + "/" + q);
			queryVideo.extractFeature();
		}catch (Exception e) {
			System.out.println("something wrong");
			e.printStackTrace();
		}
	}

    public AllResultModel compareDB() {
	    this.storedResult = new AllResultModel();
        for (String method: queryVideo.features.keySet()) {
            Rank ranker = new Rank(videos, method);
            ranker.compare(queryVideo.features.get(method));
            storedResult.setResult(method, ranker);
        }
        return storedResult;
	}

	public AllResultModel getStoredResult() {
		return storedResult;
	}

	public void compareDBviaMethod(String method) {
		Rank ranker = new Rank(videos, method);
		ranker.compare(queryVideo.features.get(method));
		storedResult.setResult(method, ranker);
	}


	
//			System.out.println("======");
//			Rank motion = allResult.get("rgb image motion");
//			int i = 1;
//			while(!motion.scores.isEmpty()) {
//				Score s = motion.scores.poll();
//					System.out.println(i++);
//					System.out.println(s.videoName);
////					System.out.println(s.score);
//					System.out.println(s.ratio);
//					System.out.println("start frame is ");
//					System.out.println((int) (600 * s.ratio));
//					System.out.println(s.getDistribution());
//			}

}
