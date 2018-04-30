package videosearch;

public class Score {
    String videoName;
    double[] diffs;
    double score = 0;
    double ratio = 0;

    public Score(String videoName, double[] diffs, double ratio) {
        this.videoName = videoName;
        this.diffs = diffs;
        this.setScore();
        this.ratio = ratio;
    }
    // score is min diff
    public void setScore() {
        double sum = 0;
        for (double s: diffs) {
            sum += s;
        }
        this.score = -sum;
    }

    public String getVideoName() {
        return videoName;
    }

    public double getScore() {
        return score;
    }

    public double getRatio() {
        return ratio;
    }

    public double[] getDistribution() {
        double maxDiff = Double.MIN_VALUE;
        for (double diff: diffs) {
//        		System.out.print(diff + "  ");
        		maxDiff = Math.max(diff, maxDiff);
        }
        double[] score = new double[diffs.length];
        
        for (int i = 0; i < score.length; i++) {
        		if (diffs[i] == 0) {
        			score[i] = 1;
        		}
        		else {
        			score[i] = 1 - diffs[i] / maxDiff;
        		}        		
        }
        return score;
    }


}
