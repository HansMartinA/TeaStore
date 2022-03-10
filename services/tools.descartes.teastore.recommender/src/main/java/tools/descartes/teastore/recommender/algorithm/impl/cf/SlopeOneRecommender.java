package tools.descartes.teastore.recommender.algorithm.impl.cf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import tools.descartes.teastore.recommender.algorithm.AbstractRecommender;
import tools.descartes.teastore.recommender.algorithm.impl.UseFallBackException;
public class SlopeOneRecommender extends AbstractRecommender {
private Map<Long, Map<Long, Double>> differences = new  HashMap<>();

private Map<Long, Map<Long, Integer>> frequencies = new  HashMap<>();

public  Map<Long, Map<Long, Double>> getDifferences() {
return differences;
}

public  void setDifferences(Map<Long, Map<Long, Double>> differences) {
this.differences = differences;
}

public  Map<Long, Map<Long, Integer>> getFrequencies() {
return frequencies;
}

public  void setFrequencies(Map<Long, Map<Long, Integer>> frequencies) {
this.frequencies = frequencies;
}

@Override
protected  List<Long> execute(Long userid, List<Long> currentItems) {
if (userid == null)
{
throw new  UseFallBackException(this.getClass().getName() + " does not support null userids. Use a pseudouser or switch to another approach.");
}
if (getUserBuyingMatrix().get(userid) == null)
{
throw new  UseFallBackException("No user information.");
}
Map<Long, Double> importances = getUserVector(userid);
return filterRecommendations(importances, currentItems);
}

protected  Map<Long, Double> getUserVector(Long userid) {
HashMap<Long, Double> importances = new  HashMap<>();
for (Long productid : getTotalProducts())
{
try {
importances.put(productid, calculateScoreForItem(userid, productid));
}
catch(NullPointerException e){
importances.put(productid,  - 1.0D);
}
}
return importances;
}

private  double calculateScoreForItem(long userid, long itemid) {
double score = 0;
double cumWeights = 0;
for (Entry<Long, Double> useritem : getUserBuyingMatrix().get(userid).entrySet())
{
if (useritem.getKey() == itemid)
{
return useritem.getValue();
}
int frequency = frequencies.get(useritem.getKey()).get(itemid);
score += useritem.getValue() * frequency;
score += differences.get(useritem.getKey()).get(itemid) * frequency;
cumWeights += frequency;
}
return score / cumWeights;
}

@Override
protected  void executePreprocessing() {
buildDifferencesMatrices(getUserBuyingMatrix());
}

private  void buildDifferencesMatrices(Map<Long, Map<Long, Double>> userRatingMatrix) {
for (Map<Long, Double> uservalues : userRatingMatrix.values())
{
for (Entry<Long, Double> singleRating : uservalues.entrySet())
{
if (!frequencies.containsKey(singleRating.getKey()))
{
frequencies.put(singleRating.getKey(), new  HashMap<Long, Integer>());
differences.put(singleRating.getKey(), new  HashMap<Long, Double>());
}
for (Entry<Long, Double> otherRating : uservalues.entrySet())
{
int currCount = 0;
Integer count = frequencies.get(singleRating.getKey()).get(otherRating.getKey());
if (count != null)
{
currCount = count.intValue();
}
double currDiff = 0;
Double diff = differences.get(singleRating.getKey()).get(otherRating.getKey());
if (diff != null)
{
currDiff = diff.doubleValue();
}
double userdiff = singleRating.getValue() - otherRating.getValue();
frequencies.get(singleRating.getKey()).put(otherRating.getKey(), currCount + 1);
differences.get(singleRating.getKey()).put(otherRating.getKey(), currDiff + userdiff);
}
}
}
for (Long i : differences.keySet())
{
for (Long j : differences.get(i).keySet())
{
double diffval = differences.get(i).get(j);
double freq = frequencies.get(i).get(j);
differences.get(i).put(j, diffval / freq);
}
}
}

}
