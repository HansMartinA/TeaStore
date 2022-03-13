package tools.descartes.teastore.recommender.algorithm.impl.pop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import tools.descartes.teastore.recommender.algorithm.AbstractRecommender;
public class PopularityBasedRecommender extends AbstractRecommender {
private HashMap<Long, Double> counts;

@Override
protected  List<Long> execute(Long userid, List<Long> currentItems) {
return filterRecommendations(counts, currentItems);
}

@Override
protected  void executePreprocessing() {
counts = new  HashMap<>();
for (Map<Long, Double> usermap : getUserBuyingMatrix().values())
{
for (Entry<Long, Double> product : usermap.entrySet())
{
if (!counts.containsKey(product.getKey()))
{
counts.put(product.getKey(), product.getValue());
}
else
{
counts.put(product.getKey(), counts.get(product.getKey()) + product.getValue());
}
}
}
}

}
