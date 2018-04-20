package videosearch;

import java.util.List;

public class ColorFeature extends Feature {
	List<int[]> data;
	public ColorFeature(String m, List<int[]> data) {
		this.data = data;
		this.method = m;
	}
}
