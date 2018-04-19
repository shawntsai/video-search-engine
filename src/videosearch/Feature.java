package videosearch;

import java.util.ArrayList;
import java.util.List;

public class Feature {
	String method;
	List<Double> data;
	public Feature (String m, List<Double> f) {
		this.method = m;
		this.data = f;
	}
	public Feature (String m, double[] f) {
		this.method = m;
		this.data = new ArrayList<Double>();
		for (double v: f) this.data.add(v);
	}
}