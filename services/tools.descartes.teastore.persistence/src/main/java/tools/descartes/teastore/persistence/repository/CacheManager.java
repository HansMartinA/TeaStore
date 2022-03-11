package tools.descartes.teastore.persistence.repository;

import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.persistence.domain.CategoryRepository;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
import tools.descartes.teastore.registryclient.util.RESTClient;
public final class CacheManager {
private static final String ENDPOINTURI = "cache";

public static final CacheManager MANAGER = new  CacheManager();

private  CacheManager(){
}
public  void clearAllCaches() {
CategoryRepository.REPOSITORY.getEMF().getCache().evictAll();
try {
ServiceLoadBalancer.multicastRESTToOtherServiceInstances(ENDPOINTURI, String.class, client -> clearRemoteCacheREST(client, null));
}
catch(Exception e){
}
}

public  void clearCache(Class<?> entityClass) {
clearLocalCacheOnly(entityClass);
clearRemoteCache(entityClass);
}

public  List<String> clearRemoteCache(Class<?> entityClass) {
List<String> responses = null;
try {
responses = ServiceLoadBalancer.multicastRESTToOtherServiceInstances(ENDPOINTURI, String.class, client -> clearRemoteCacheREST(client, entityClass));
}
catch(Exception e){
}
return responses;
}

public  void clearLocalCacheOnly(Class<?> entityClass) {
CategoryRepository.REPOSITORY.getEMF().getCache().evict(entityClass);
}

public  void clearLocalCacheOnly() {
CategoryRepository.REPOSITORY.getEMF().getCache().evictAll();
}

private  String clearRemoteCacheREST(RESTClient<String> client, Class<?> entityClass) {
WebTarget target = client.getService().path(client.getApplicationURI()).path(client.getEndpointURI());
if (entityClass != null)
{
target = target.path("class").path(entityClass.getName());
}
else
{
target = target.path("cache");
}
Response response = target.request(MediaType.TEXT_PLAIN).delete();
String message = null;
if (response.getStatus() == 200)
{
message = response.readEntity(String.class);
}
response.close();
return message;
}

public  List<String> resetAllEMFs() {
resetLocalEMF();
return resetRemoteEMFs();
}

public  List<String> resetRemoteEMFs() {
List<String> responses = null;
try {
responses = ServiceLoadBalancer.multicastRESTToOtherServiceInstances(ENDPOINTURI, String.class, client -> resetRemoteEMF(client));
}
catch(Exception e){
}
return responses;
}

public  void resetLocalEMF() {
EMFManager.clearEMF();
}

private  String resetRemoteEMF(RESTClient<String> client) {
WebTarget target = client.getEndpointTarget().path("emf");
Response response = target.request(MediaType.TEXT_PLAIN).delete();
String message = null;
if (response.getStatus() == 200)
{
message = response.readEntity(String.class);
}
response.close();
return message;
}

}
