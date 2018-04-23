package ui.model;

import org.jfree.data.category.CategoryDataset;
import videosearch.Rank;
import videosearch.Score;

import java.util.HashMap;
import java.util.Map;

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

    private String[] buildListFromDB(String method) {
        Rank ranker = getRankOnMethod(method);
        int size = 0;

        if(ranker != null) {
            size = ranker.getScores().size();
        }else {
            return null;
        }
        String[] output = new String[size];
        int i = 0;
        while(!ranker.getScores().isEmpty()) {
            Score s = ranker.getScores().poll();
            output[i] = s.getVideoName();
            i++;
        }
        cacheListResult.put(method,output);
        return output;
    }

    public boolean isResultEmpty() {
        return this.allResult.size() > 0 ? false: true;
    }


}
