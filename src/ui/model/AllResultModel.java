package ui.model;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import videosearch.Rank;
import videosearch.Score;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class AllResultModel {
    // For these two map, key is the descriptor
    private Map<String, Rank> allResult = new HashMap<>();
    private Map<String, String[]> cacheListResult = new HashMap<>();
    private Map<String, Map<String, CategoryDataset>> cacheDistributionDataset = new HashMap<>();

    public void setResult(String method, Rank result) {
        this.allResult.put(method, result);
    }

    public Rank getRankOnMethod(String method) {
        return allResult.get(method);
    }

    public String[] getResultList(String method) {
        String[] output = cacheListResult.get(method);
        if(output == null)
            return buildListFromDB(method);

        return output;
    }

    public CategoryDataset getDistribution(String method, String videoName) {
        return this.cacheDistributionDataset.get(method).get(videoName);
    }

    private String[] buildListFromDB(String method) {
        Rank ranker = getRankOnMethod(method);
        int size;

        if(ranker != null) {
            size = ranker.getScores().size();
        }else {
            return null;
        }
        String[] output = new String[size];
        int i = 0;
        Map<String, CategoryDataset> videoDistribution = new HashMap<>();
        while(!ranker.getScores().isEmpty()) {
            Score s = ranker.getScores().poll();
            output[i] = s.getVideoName();
            storeDistributionDataset(s, videoDistribution);
            i++;
        }
        cacheListResult.put(method,output);
        cacheDistributionDataset.put(method, videoDistribution);
        return output;
    }

    private void storeDistributionDataset(Score videoScore, Map<String, CategoryDataset>distribution) {
        CategoryDataset dataSet = createDataset(videoScore.getDistribution());
        distribution.put(videoScore.getVideoName(), dataSet);
    }

    private CategoryDataset createDataset(double[] data) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for(int i = 0; i < data.length; i++) {
            dataSet.addValue(data[i],"similarity", Integer.toString(i));
            System.out.println(data[i]);
        }
        return dataSet;
    }

    public boolean isResultEmpty() {
        return this.allResult.size() > 0 ? false: true;
    }


}
