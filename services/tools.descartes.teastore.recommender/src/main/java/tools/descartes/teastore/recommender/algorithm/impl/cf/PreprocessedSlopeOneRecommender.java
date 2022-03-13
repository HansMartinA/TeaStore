package tools.descartes.teastore.recommender.algorithm.impl.cf;

import java.util.HashMap;
import java.util.Map;
public class PreprocessedSlopeOneRecommender extends SlopeOneRecommender {
private Map<Long, Map<Long, Double>> predictedRatings;

public  Map<Long, Map<Long, Double>> getPredictedRatings() {
return predictedRatings;
}

public  void setPredictedRatings(Map<Long, Map<Long, Double>> predictedRatings) {
this.predictedRatings = predictedRatings;
}

@Override
protected  Map<Long, Double> getUserVector(Long userid) {
return predictedRatings.get(userid);
}

@Override
protected  void executePreprocessing() {
super.executePreprocessing();
predictedRatings = new  HashMap<>();
for (Long userid : getUserBuyingMatrix().keySet())
{
Map<Long, Double> pred = super.getUserVector(userid);
predictedRatings.put(userid, pred);
}
}

}
