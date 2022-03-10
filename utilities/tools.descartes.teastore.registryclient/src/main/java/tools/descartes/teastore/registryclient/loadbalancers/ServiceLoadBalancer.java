package tools.descartes.teastore.registryclient.loadbalancers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import rx.Observable;
import tools.descartes.teastore.registryclient.RegistryClient;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.registryclient.util.RESTClient;
public final class ServiceLoadBalancer {
private static final Logger LOG = LoggerFactory.getLogger(ServiceLoadBalancer.class);

private static ConcurrentHashMap<String, ServiceLoadBalancer> serviceMap = new  ConcurrentHashMap<>();

private ConcurrentHashMap<String, EndpointClientCollection<?>> endpointMap = new  ConcurrentHashMap<>();

private final Service targetService;

private Set<Server> serviceServers = new  HashSet<Server>();

private BaseLoadBalancer loadBalancer;

private final RetryHandler retryHandler = new  DefaultLoadBalancerRetryHandler(0, 2, true);

private ReadWriteLock loadBalancerModificationLock = new  ReentrantReadWriteLock();

private  ServiceLoadBalancer(final Service targetService){
this.targetService = targetService;
}
public static  void preInitializeServiceLoadBalancers(Service  ...targetServices) {
for (Service service : targetServices)
{
getServiceLoadBalancer(service);
LOG.info("Pre-initializing client-side load balancer for target: " + getServiceLoadBalancer(service).targetService.getServiceName());
}
}

private static  ServiceLoadBalancer getServiceLoadBalancer(Service targetService) {
ServiceLoadBalancer serviceBalancer = serviceMap.get(targetService.getServiceName());
if (serviceBalancer == null || serviceBalancer.serviceServers == null || serviceBalancer.serviceServers.isEmpty())
{
serviceMap.putIfAbsent(targetService.getServiceName(), new  ServiceLoadBalancer(targetService));
updateLoadBalancersForServiceUsingRegistry(targetService);
}
return serviceMap.get(targetService.getServiceName());
}

static  ServiceLoadBalancer getServiceLoadBalancer(Service targetService, List<Server> knownServers) {
ServiceLoadBalancer serviceBalancer = ServiceLoadBalancer.serviceMap.get(targetService.getServiceName());
if (serviceBalancer == null || serviceBalancer.serviceServers == null || serviceBalancer.serviceServers.isEmpty())
{
serviceMap.putIfAbsent(targetService.getServiceName(), new  ServiceLoadBalancer(targetService));
updateLoadBalancersForService(targetService, knownServers);
}
return serviceMap.get(targetService.getServiceName());
}

@SuppressWarnings("unchecked")
private <T>  EndpointClientCollection<T> getEndpointClientCollection(String endpointURI, Class<T> entityClass) {
EndpointClientCollection<?> endpointCollection = endpointMap.get(endpointURI);
if (endpointCollection == null)
{
endpointMap.putIfAbsent(endpointURI, new  EndpointClientCollection<T>(targetService, endpointURI, entityClass));
endpointMap.get(endpointURI).updateServers(serviceServers);
}
endpointCollection = endpointMap.get(endpointURI);
return (EndpointClientCollection<T>) endpointCollection;
}

static  void updateLoadBalancersForKnownServicesUsingRegistry() {
serviceMap.values().forEach(balancer -> updateLoadBalancersForServiceUsingRegistry(balancer.targetService));
}

private static  void updateLoadBalancersForServiceUsingRegistry(Service targetService) {
List<Server> servers = RegistryClient.getClient().getServersForService(targetService);
updateLoadBalancersForService(targetService, servers);
}

static  void updateLoadBalancersForService(Service targetService, List<Server> newServers) {
ServiceLoadBalancer serviceBalancer = serviceMap.get(targetService.getServiceName());
if (serviceBalancer == null)
{
return;
}
serviceBalancer.updateLoadBalancer(newServers);
}

private  void updateLoadBalancer(List<Server> newServers) {
if (serviceServers == null)
{
serviceServers = new  HashSet<Server>();
}
if (newServers == null)
{
newServers = new  ArrayList<Server>();
}
if ((serviceServers.isEmpty() && newServers.isEmpty()) || (newServers.size() == serviceServers.size() && serviceServers.containsAll(newServers)))
{
return;
}
serviceServers = new  HashSet<Server>(newServers);
loadBalancerModificationLock.writeLock().lock();
try {
if (loadBalancer != null)
{
loadBalancer.shutdown();
}
loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(newServers);
for (EndpointClientCollection<?> lb : endpointMap.values())
{
lb.updateServers(newServers);
}
}
finally {
loadBalancerModificationLock.writeLock().unlock();
}
}

public static <T, R>  R loadBalanceRESTOperation(Service targetService, String endpointURI, Class<T> entityClass, Function<RESTClient<T>, R> operation)throws NotFoundException, LoadBalancerTimeoutException {
return getServiceLoadBalancer(targetService).loadBalanceRESTOperation(endpointURI, entityClass, operation);
}

private <T, R>  R loadBalanceRESTOperation(String endpointURI, Class<T> entityClass, Function<RESTClient<T>, R> operation)throws NotFoundException, LoadBalancerTimeoutException {
R r = null;
loadBalancerModificationLock.readLock().lock();
try {
if (loadBalancer == null)
{
LOG.warn("Load Balancer was not initialized for service: " + targetService.getServiceName() + ". Is Registry up?");
updateLoadBalancersForServiceUsingRegistry(targetService);
}
if (loadBalancer == null || loadBalancer.getAllServers().isEmpty())
{
LOG.warn("No Server registered for Service: " + targetService.getServiceName());
}
else
{
ServiceLoadBalancerResult<R> slbr = LoadBalancerCommand.<ServiceLoadBalancerResult<R>>builder().withLoadBalancer(loadBalancer).withRetryHandler(retryHandler).build().submit(server -> Observable.just(ServiceLoadBalancerResult.fromRESTOperation((RESTClient<T>) getEndpointClientCollection(endpointURI, entityClass).getRESTClient(server), operation))).onErrorReturn((Throwable e) -> null).toBlocking().first();
if (slbr == null)
{
throw new  NullPointerException("ServiceLoadBalancerResult was null!");
}
if (slbr.getStatusCode() == Status.REQUEST_TIMEOUT.getStatusCode())
{
throw new  LoadBalancerTimeoutException("Timout at endpoint: " + endpointURI + ", with target service: " + targetService.getServiceName(), targetService);
}
else
if (slbr.getStatusCode() == Status.NOT_FOUND.getStatusCode() || slbr.getEntity() == null)
{
throw new  NotFoundException();
}
r = slbr.getEntity();
}
}
finally {
loadBalancerModificationLock.readLock().unlock();
}
return r;
}

public static <T, R>  List<R> multicastRESTOperation(Service targetService, String endpointURI, Class<T> entityClass, Function<RESTClient<T>, R> operation) {
return getServiceLoadBalancer(targetService).multicastRESTOperation(endpointURI, entityClass, operation, null);
}

public static <T, R>  List<R> multicastRESTToOtherServiceInstances(String endpointURI, Class<T> entityClass, Function<RESTClient<T>, R> operation) {
return getServiceLoadBalancer(RegistryClient.getClient().getMyService()).multicastRESTOperation(endpointURI, entityClass, operation, RegistryClient.getClient().getMyServiceInstanceServer());
}

private <T, R>  List<R> multicastRESTOperation(String endpointURI, Class<T> entityClass, Function<RESTClient<T>, R> operation, Server exception) {
List<R> responses = new  ArrayList<>();
List<Server> servers = null;
loadBalancerModificationLock.readLock().lock();
try {
if (loadBalancer != null)
{
servers = new  ArrayList<>(loadBalancer.getAllServers());
}
if (servers != null)
{
if (exception != null)
{
servers.remove(exception);
}
responses = servers.parallelStream().map(server -> {
try {
return operation.apply((RESTClient<T>) getEndpointClientCollection(endpointURI, entityClass).getRESTClient(server));
}
catch(Exception e){
return null;
}
}
).collect(Collectors.toList());
}
}
finally {
loadBalancerModificationLock.readLock().unlock();
}
return responses;
}

}
