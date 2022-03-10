package tools.descartes.teastore.recommender.algorithm.impl.orderbased;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import tools.descartes.teastore.recommender.algorithm.AbstractRecommender;
import tools.descartes.teastore.recommender.algorithm.OrderItemSet;
import tools.descartes.teastore.recommender.algorithm.impl.UseFallBackException;
public class OrderBasedRecommender extends AbstractRecommender {
@Override
protected  List<Long> execute(Long userid, List<Long> currentItems) {
HashMap<Long, Double> counts = new  HashMap<>();
for (Long product : currentItems)
{
addAllCountsOfProduct(counts, product);
}
if (counts.isEmpty())
{
throw new  UseFallBackException("No item was bought together with the current cart. Therefore, all counts are 0.");
}
return filterRecommendations(counts, currentItems);
}

private  void addAllCountsOfProduct(HashMap<Long, Double> counts, Long product) {
for (Set<OrderItemSet> set : getUserItemSets().values())
{
for (OrderItemSet orderset : set)
{
if (orderset.getOrderset().keySet().contains(product))
{
for (Long o : orderset.getOrderset().keySet())
{
if (counts.containsKey(o))
{
counts.put(o, counts.get(o) + 1);
}
else
{
counts.put(o, 1.0D);
}
}
}
}
}
}

}
