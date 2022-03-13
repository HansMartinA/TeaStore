package tools.descartes.teastore.recommender.servlet;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.recommender.algorithm.RecommenderSelector;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
import tools.descartes.teastore.registryclient.rest.LoadBalancedCRUDOperations;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.OrderItem;
public final class TrainingSynchronizer {
public static final long DEFAULT_MAX_TIME_VALUE = Long.MIN_VALUE;

private static final int PERSISTENCE_CREATION_MAX_WAIT_TIME = 120000;

private static final List<Integer> PERSISTENCE_CREATION_WAIT_TIME = Arrays.asList(1000, 2000, 5000, 10000, 30000, 60000);

private static TrainingSynchronizer instance;

private boolean isReady = false;

public  boolean isReady() {
return isReady;
}

public  void setReady(boolean isReady) {
this.isReady = isReady;
}

private  TrainingSynchronizer(){
}
public static  TrainingSynchronizer getInstance() {
if (instance == null)
{
instance = new  TrainingSynchronizer();
}
return instance;
}

private static final Logger LOG = LoggerFactory.getLogger(TrainingSynchronizer.class);

private long maxTime = DEFAULT_MAX_TIME_VALUE;

public  long getMaxTime() {
return maxTime;
}

public  void setMaxTime(String maxTime) {
setMaxTime(toMillis(maxTime));
}

public  void setMaxTime(long maxTime) {
this.maxTime = maxTime;
}

private  void waitForPersistence() {
Iterator<Integer> waitTimes = PERSISTENCE_CREATION_WAIT_TIME.iterator();
while (true)
{
Response result = null;
try {
result = ServiceLoadBalancer.loadBalanceRESTOperation(Service.PERSISTENCE, "generatedb", String.class, client -> client.getService().path(client.getApplicationURI()).path(client.getEndpointURI()).path("finished").request().get());
if (result != null && Boolean.parseBoolean(result.readEntity(String.class)))
{
break;
}
}
catch(NotFoundException | LoadBalancerTimeoutException e){
}
finally {
if (result != null)
{
result.close();
}
}
try {
int nextWaitTime;
if (waitTimes.hasNext())
{
nextWaitTime = waitTimes.next();
}
else
{
nextWaitTime = PERSISTENCE_CREATION_MAX_WAIT_TIME;
}
LOG.info("Persistence not reachable. Waiting for {}ms.", nextWaitTime);
Thread.sleep(nextWaitTime);
}
catch(InterruptedException interrupted){
LOG.warn("Thread interrupted while waiting for persistence to be available.", interrupted);
}
}
}

public  long retrieveDataAndRetrain() {
setReady(false);
LOG.trace("Retrieving data objects from database...");
waitForPersistence();
List<OrderItem> items = null;
List<Order> orders = null;
try {
items = LoadBalancedCRUDOperations.getEntities(Service.PERSISTENCE, "orderitems", OrderItem.class,  - 1,  - 1);
long noItems = items.size();
LOG.trace("Retrieved " + noItems + " orderItems, starting retrieving of orders now.");
}
catch(Exception e){
setReady(true);
LOG.error("Database retrieving failed.");
return  - 1;
}
try {
orders = LoadBalancedCRUDOperations.getEntities(Service.PERSISTENCE, "orders", Order.class,  - 1,  - 1);
long noOrders = orders.size();
LOG.trace("Retrieved " + noOrders + " orders, starting training now.");
}
catch(Exception e){
setReady(true);
LOG.error("Database retrieving failed.");
return  - 1;
}
filterLists(items, orders);
RecommenderSelector.getInstance().train(items, orders);
LOG.trace("Finished training, ready for recommendation.");
setReady(true);
return items.size() + orders.size();
}

private  void filterLists(List<OrderItem> orderItems, List<Order> orders) {
List<Response> maxTimeResponses = ServiceLoadBalancer.multicastRESTOperation(Service.RECOMMENDER, "train/timestamp", Response.class, client -> client.getService().path(client.getApplicationURI()).path(client.getEndpointURI()).request(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get());
for (Response response : maxTimeResponses)
{
if (response == null)
{
LOG.warn("One service response was null and is therefore not available for time-check.");
}
else
if (response.getStatus() == Response.Status.OK.getStatusCode())
{
long milliTS = response.readEntity(Long.class);
if (maxTime != TrainingSynchronizer.DEFAULT_MAX_TIME_VALUE && maxTime != milliTS)
{
LOG.warn("Services disagree about timestamp: " + maxTime + " vs " + milliTS + ". Therfore using the minimum.");
}
maxTime = Math.min(maxTime, milliTS);
}
else
{
response.bufferEntity();
LOG.warn("Service " + response + "was not available for time-check.");
}
}
if (maxTime == Long.MIN_VALUE)
{
for (Order or : orders)
{
maxTime = Math.max(maxTime, toMillis(or.getTime()));
}
}
filterForMaxtimeStamp(orderItems, orders);
}

private  void filterForMaxtimeStamp(List<OrderItem> orderItems, List<Order> orders) {
List<Order> remove = new  ArrayList<>();
for (Order or : orders)
{
if (toMillis(or.getTime()) > maxTime)
{
remove.add(or);
}
}
orders.removeAll(remove);
List<OrderItem> removeItems = new  ArrayList<>();
for (OrderItem orderItem : orderItems)
{
boolean contained = false;
for (Order or : orders)
{
if (or.getId() == orderItem.getOrderId())
{
contained = true;
}
}
if (!contained)
{
removeItems.add(orderItem);
}
}
orderItems.removeAll(removeItems);
}

private  long toMillis(String date) {
TemporalAccessor temporalAccessor = DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(date);
LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
Instant instant = Instant.from(zonedDateTime);
return instant.toEpochMilli();
}

}
