package videosearch;

import java.util.List;

public class Feature {
	String method;
	List<Double> data;
	public Feature (String m, List<Double> f) {
		this.method = m;
		this.data = f;
	}
}