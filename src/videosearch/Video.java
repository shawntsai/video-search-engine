package videosearch;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Video {
	String name;
	String path;
	Map<String, List<Double>> features = new HashMap<>();
	public Video(String name, String path) {
		this.name = name;
		this.path = path;		
	}
	
	public void extractFeature() {
		SoundExtractor extractor = new SoundExtractor(new File(path), 2048, 0);
		for (Feature f: extractor.features) {
			this.features.put(f.method, f.data);
		}
	}
	
	
	
}
 