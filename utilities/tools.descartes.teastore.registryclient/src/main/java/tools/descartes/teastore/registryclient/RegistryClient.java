package tools.descartes.teastore.registryclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.loadbalancer.Server;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerUpdaterDaemon;
public class RegistryClient {
private static final Logger LOG = LoggerFactory.getLogger(RegistryClient.class);

private static RegistryClient client = new  RegistryClient();

private String registryRESTURL;

private String hostName = null;

private Integer port = null;

private Server myServiceInstanceServer = null;

private Service myService = null;

private static final int LOAD_BALANCER_REFRESH_INTERVAL_MS = 2500;

private static final int HEARTBEAT_INTERVAL_MS = 2500;

private ScheduledExecutorService loadBalancerUpdateScheduler = Executors.newSingleThreadScheduledExecutor();

private ScheduledExecutorService heartbeatScheduler = Executors.newSingleThreadScheduledExecutor();

private ScheduledExecutorService availabilityScheduler = Executors.newSingleThreadScheduledExecutor();

protected  RegistryClient(){
System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
String useHostIP = "false";
try {
useHostIP = (String) new  InitialContext().lookup("java:comp/env/useHostIP");
}
catch(NamingException e){
LOG.warn("useHostIP not set. Not using host ip as hostname.");
}
try {
if (useHostIP.equalsIgnoreCase("true"))
{
hostName = InetAddress.getLocalHost().getHostAddress();
}
else
{
hostName = (String) new  InitialContext().lookup("java:comp/env/hostName");
}
}
catch(NamingException e){
LOG.warn("hostName not set. Using default OS-provided hostname.");
}
catch(UnknownHostException e){
LOG.error("could not resolve host IP. Using default OS-provided hostname: " + e.getMessage());
}
try {
port = Integer.parseInt((String) new  InitialContext().lookup("java:comp/env/servicePort"));
}
catch(NamingException | NumberFormatException e){
LOG.warn("Could not read servicePort! Using port 8080 as fallback.");
port = 8080;
}
try {
registryRESTURL = (String) new  InitialContext().lookup("java:comp/env/registryURL");
}
catch(NamingException e){
LOG.warn("registryURL not set. Falling back to default registry URL (localhost, port " + port + ").");
registryRESTURL = "http://localhost:" + port + "/tools.descartes.teastore.registry/rest/services/";
}
}
public static  RegistryClient getClient() {
return client;
}

public  void unregister(String contextPath) {
Service service = getService(contextPath);
Server host = getServer();
LOG.info("Shutting down " + service.getServiceName() + "@" + host);
heartbeatScheduler.shutdownNow();
loadBalancerUpdateScheduler.shutdownNow();
availabilityScheduler.shutdownNow();
try {
loadBalancerUpdateScheduler.awaitTermination(20, TimeUnit.SECONDS);
heartbeatScheduler.awaitTermination(20, TimeUnit.SECONDS);
availabilityScheduler.awaitTermination(20, TimeUnit.SECONDS);
RegistryClient.client.unregisterOnce(service, host);
}
catch(ProcessingException e){
LOG.warn("Could not unregister " + service.getServiceName() + " when it was shutting " + "down, since it could not reach the registry. This can be caused by shutting " + "down the registry before other services, but is in it self not a problem.");
}
catch(InterruptedException e){
e.printStackTrace();
}
}

public  void register(String contextPath) {
Service service = getService(contextPath);
Server host = getServer();
heartbeatScheduler.scheduleAtFixedRate(new  RegistryClientHeartbeatDaemon(service, host), 0, HEARTBEAT_INTERVAL_MS, TimeUnit.MILLISECONDS);
loadBalancerUpdateScheduler.scheduleAtFixedRate(new  LoadBalancerUpdaterDaemon(), 1000, LOAD_BALANCER_REFRESH_INTERVAL_MS, TimeUnit.MILLISECONDS);
}

public  void runAfterServiceIsAvailable(Service requestedService, StartupCallback callback, Service myService) {
availabilityScheduler.schedule(new  StartupCallbackTask(requestedService, callback, myService), 0, TimeUnit.NANOSECONDS);
availabilityScheduler.shutdown();
}

public  List<Server> getServersForService(Service targetService) {
List<String> list = null;
List<Server> serverList = new  ArrayList<Server>();
try {
Response response = getRESTClient(5000).target(registryRESTURL).path("/" + targetService.getServiceName() + "/").request(MediaType.APPLICATION_JSON).get();
list = response.readEntity(new  GenericType<List<String>>(){
}
);
}
catch(ProcessingException e){
return null;
}
if (list != null)
{
for (String string : list)
{
serverList.add(new  Server(string));
}
}
return serverList;
}

public  Server getMyServiceInstanceServer() {
return myServiceInstanceServer;
}

public  Service getMyService() {
return myService;
}

protected  boolean registerOnce(Service service, Server server) {
myService = service;
myServiceInstanceServer = server;
try {
Response response = getRESTClient(5000).target(registryRESTURL).path(service.getServiceName()).path(server.toString()).request(MediaType.APPLICATION_JSON).put(Entity.text(""));
return (response.getStatus() == Response.Status.OK.getStatusCode());
}
catch(ProcessingException e){
return false;
}
}

private  boolean unregisterOnce(Service service, Server server) {
try {
Response response = getRESTClient(1000).target(registryRESTURL).path(service.getServiceName()).path(server.toString()).request(MediaType.APPLICATION_JSON).delete();
return (response.getStatus() == Response.Status.OK.getStatusCode());
}
catch(ProcessingException e){
return false;
}
}

private  Client getRESTClient(int timeout) {
ClientConfig configuration = new  ClientConfig();
configuration.property(ClientProperties.CONNECT_TIMEOUT, timeout);
configuration.property(ClientProperties.READ_TIMEOUT, timeout);
return ClientBuilder.newClient(configuration);
}

private  Service getService(String serviceName) {
serviceName = cleanupServiceName(serviceName);
for (Service service : Service.values())
{
if (service.getServiceName().equals(serviceName))
{
return service;
}
}
throw new  IllegalStateException("The service " + serviceName + " is not registered in the Services enum");
}

private  Server getServer() {
return new  Server(getHostName(), getPort());
}

private  String getHostName() {
if (hostName != null && !hostName.isEmpty())
{
return hostName;
}
try {
return InetAddress.getLocalHost().getCanonicalHostName();
}
catch(UnknownHostException e){
throw new  IllegalStateException("could not load hostname from OS.");
}
}

private  int getPort() {
if (port != null)
{
return port;
}
else
{
throw new  IllegalStateException("Could not read servicePort!");
}
}

protected  String cleanupServiceName(String serviceName) {
return serviceName.replace("/", "");
}

protected  ScheduledExecutorService getHeartbeatScheduler() {
return heartbeatScheduler;
}

protected  ScheduledExecutorService getLoadBalancerUpdateScheduler() {
return loadBalancerUpdateScheduler;
}

}
