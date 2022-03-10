package tools.descartes.teastore.recommender.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.OrderItem;
import tools.descartes.teastore.entities.Product;
import tools.descartes.teastore.entities.User;
public abstract class AbstractRecommender implements IRecommender {
private boolean trainingFinished = false;

public static final int MAX_NUMBER_OF_RECOMMENDATIONS = 10;

private static final Logger LOG = LoggerFactory.getLogger(AbstractRecommender.class);

private Map<Long, Map<Long, Double>> userBuyingMatrix;

private Map<Long, Set<OrderItemSet>> userItemSets;

private Set<Long> totalProducts;

@Override
public  void train(List<OrderItem> orderItems, List<Order> orders) {
long tic = System.currentTimeMillis();
totalProducts = new  HashSet<>();
Map<Long, OrderItemSet> unOrderizeditemSets = new  HashMap<>();
for (OrderItem orderItem : orderItems)
{
if (!unOrderizeditemSets.containsKey(orderItem.getOrderId()))
{
unOrderizeditemSets.put(orderItem.getOrderId(), new  OrderItemSet());
unOrderizeditemSets.get(orderItem.getOrderId()).setOrderId(orderItem.getOrderId());
}
unOrderizeditemSets.get(orderItem.getOrderId()).getOrderset().put(orderItem.getProductId(), orderItem.getQuantity());
if (!totalProducts.contains(orderItem.getProductId()))
{
totalProducts.add(orderItem.getProductId());
}
}
Map<Order, OrderItemSet> itemSets = new  HashMap<>();
for (Long orderid : unOrderizeditemSets.keySet())
{
Order realOrder = findOrder(orders, orderid);
itemSets.put(realOrder, unOrderizeditemSets.get(orderid));
}
userItemSets = new  HashMap<>();
for (Order order : itemSets.keySet())
{
if (!userItemSets.containsKey(order.getUserId()))
{
userItemSets.put(order.getUserId(), new  HashSet<OrderItemSet>());
}
itemSets.get(order).setUserId(order.getUserId());
userItemSets.get(order.getUserId()).add(itemSets.get(order));
}
userBuyingMatrix = createUserBuyingMatrix(userItemSets);
executePreprocessing();
LOG.info("Training recommender finished. Training took: " + (System.currentTimeMillis() - tic) + "ms.");
trainingFinished = true;
}

protected  void executePreprocessing() {
}

@Override
public  List<Long> recommendProducts(Long userid, List<OrderItem> currentItems)throws UnsupportedOperationException {
if (!trainingFinished)
{
throw new  UnsupportedOperationException("This instance is not fully trained yet.");
}
if (currentItems.isEmpty())
{
return new  LinkedList<>();
}
List<Long> items = new  ArrayList<>(currentItems.size());
for (OrderItem item : currentItems)
{
items.add(item.getProductId());
}
return execute(userid, items);
}

protected  List<Long> filterRecommendations(Map<Long, Double> priorityList, List<Long> currentItems) {
TreeMap<Double, List<Long>> ranking = createRanking(priorityList);
List<Long> reco = new  ArrayList<>(MAX_NUMBER_OF_RECOMMENDATIONS);
for (Double score : ranking.descendingKeySet())
{
List<Long> productIds = ranking.get(score);
for (long productId : productIds)
{
if (reco.size() < MAX_NUMBER_OF_RECOMMENDATIONS)
{
if (!currentItems.contains(productId))
{
reco.add(productId);
}
}
else
{
return reco;
}
}
}
return reco;
}

private  TreeMap<Double, List<Long>> createRanking(Map<Long, Double> map) {
TreeMap<Double, List<Long>> ranking = new  TreeMap<Double, List<Long>>();
for (Entry<Long, Double> entry : map.entrySet())
{
List<Long> productIds = ranking.get(entry.getValue());
if (productIds == null)
{
productIds = new  ArrayList<>();
ranking.put(entry.getValue(), productIds);
}
productIds.add(entry.getKey());
}
return ranking;
}

protected abstract  List<Long> execute(Long userid, List<Long> currentItems) ;

private  Order findOrder(List<Order> orders, long orderid) {
for (Order order : orders)
{
if (order.getId() == orderid)
{
return order;
}
}
return null;
}

public  Map<Long, Map<Long, Double>> getUserBuyingMatrix() {
return userBuyingMatrix;
}

public  void setUserBuyingMatrix(Map<Long, Map<Long, Double>> userBuyingMatrix) {
this.userBuyingMatrix = userBuyingMatrix;
}

public  Set<Long> getTotalProducts() {
return totalProducts;
}

public  void setTotalProducts(Set<Long> totalProducts) {
this.totalProducts = totalProducts;
}

public  Map<Long, Set<OrderItemSet>> getUserItemSets() {
return userItemSets;
}

public  void setUserItemSets(Map<Long, Set<OrderItemSet>> userItemSets) {
this.userItemSets = userItemSets;
}

private static  Map<Long, Map<Long, Double>> createUserBuyingMatrix(Map<Long, Set<OrderItemSet>> useritemsets) {
Map<Long, Map<Long, Double>> matrix = new  HashMap<>();
for (Entry<Long, Set<OrderItemSet>> entry : useritemsets.entrySet())
{
Map<Long, Double> line = new  HashMap<>();
for (OrderItemSet orderset : entry.getValue())
{
for (Entry<Long, Integer> product : orderset.getOrderset().entrySet())
{
if (!line.containsKey(product.getKey()))
{
line.put(product.getKey(), Double.valueOf(product.getValue()));
}
else
{
line.put(product.getKey(), Double.valueOf(line.get(product.getKey()) + product.getValue()));
}
}
}
matrix.put(entry.getKey(), line);
}
return matrix;
}

}
