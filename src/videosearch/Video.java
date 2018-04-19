package videosearch;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Video {
	String name;
	String path;
	String imagePath;
	Map<String, List<Double>> features = new HashMap<>();
	public Video(String name, String path, String imagePath) {
		this.name = name;
		this.path = path;		
		this.imagePath = imagePath;
	}
	
	public void extractFeature() {
		SoundExtractor extractor = new SoundExtractor(new File(path), 2048, 0);
		for (Feature f: extractor.features) {
			this.features.put(f.method, f.data);
		}
		
		ImageFeatureExtractor imageExtractor = new ImageFeatureExtractor(new File(imagePath));
		
		
	}
	
	
	
}
 