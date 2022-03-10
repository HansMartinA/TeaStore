package tools.descartes.teastore.recommender.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.recommender.algorithm.impl.UseFallBackException;
import tools.descartes.teastore.recommender.algorithm.impl.cf.PreprocessedSlopeOneRecommender;
import tools.descartes.teastore.recommender.algorithm.impl.cf.SlopeOneRecommender;
import tools.descartes.teastore.recommender.algorithm.impl.orderbased.OrderBasedRecommender;
import tools.descartes.teastore.recommender.algorithm.impl.pop.PopularityBasedRecommender;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.OrderItem;
public final class RecommenderSelector implements IRecommender {
private static Map<String, Class<? extends IRecommender>> recommenders = new  HashMap<>();

static {
recommenders = new  HashMap<String, Class<? extends IRecommender>>();
recommenders.put("Popularity", PopularityBasedRecommender.class);
recommenders.put("SlopeOne", SlopeOneRecommender.class);
recommenders.put("PreprocessedSlopeOne", PreprocessedSlopeOneRecommender.class);
recommenders.put("OrderBased", OrderBasedRecommender.class);
}
private static final Class<? extends IRecommender> DEFAULT_RECOMMENDER = SlopeOneRecommender.class;

private static final Logger LOG = LoggerFactory.getLogger(RecommenderSelector.class);

private static RecommenderSelector instance;

private IRecommender fallbackrecommender;

private IRecommender recommender;

private  RecommenderSelector(){
fallbackrecommender = new  PopularityBasedRecommender();
try {
String recommendername = (String) new  InitialContext().lookup("java:comp/env/recommenderAlgorithm");
if (recommenders.containsKey(recommendername))
{
recommender = recommenders.get(recommendername).newInstance();
}
else
{
LOG.warn("Recommendername: " + recommendername + " was not found. Using default recommender (SlopeOneRecommeder).");
recommender = DEFAULT_RECOMMENDER.newInstance();
}
}
catch(InstantiationException | IllegalAccessException e){
e.printStackTrace();
LOG.warn("Could not create an instance of the requested recommender. Using fallback.");
recommender = fallbackrecommender;
}
catch(NamingException e){
LOG.info("Recommender not set. Using default recommender (SlopeOneRecommeder).");
try {
recommender = DEFAULT_RECOMMENDER.newInstance();
}
catch(InstantiationException | IllegalAccessException e1){
e1.printStackTrace();
LOG.warn("Could not create an instance of DEFAULT_RECOMMENDER " + DEFAULT_RECOMMENDER.getName() + ".");
recommender = fallbackrecommender;
}
}
}
@Override
public  List<Long> recommendProducts(Long userid, List<OrderItem> currentItems)throws UnsupportedOperationException {
try {
return recommender.recommendProducts(userid, currentItems);
}
catch(UseFallBackException e){
LOG.trace("Executing " + recommender.getClass().getName() + " as recommender failed. Using fallback recommender. Reason:\n" + e.getMessage());
return fallbackrecommender.recommendProducts(userid, currentItems);
}
catch(UnsupportedOperationException e){
LOG.error("Executing " + recommender.getClass().getName() + " threw an UnsupportedOperationException. The recommender was not finished with training.");
throw e;
}
catch(Exception e){
LOG.warn("Executing " + recommender.getClass().getName() + " threw an unexpected error. Using fallback recommender. Reason:\n" + e.getMessage());
return fallbackrecommender.recommendProducts(userid, currentItems);
}
}

public static  RecommenderSelector getInstance() {
if (instance == null)
{
instance = new  RecommenderSelector();
}
return instance;
}

@Override
public  void train(List<OrderItem> orderItems, List<Order> orders) {
recommender.train(orderItems, orders);
fallbackrecommender.train(orderItems, orders);
}

}
