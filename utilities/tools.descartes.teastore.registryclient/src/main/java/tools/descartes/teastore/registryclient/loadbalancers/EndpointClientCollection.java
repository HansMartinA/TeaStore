package tools.descartes.teastore.registryclient.loadbalancers;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.netflix.loadbalancer.Server;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.util.RESTClient;
public class EndpointClientCollection<T>  {
private ConcurrentHashMap<Server, RESTClient<T>> clients = new  ConcurrentHashMap<>();

private final Class<T> entityClass;

private final Service targetService;

private final String endpointURI;

 EndpointClientCollection(Service targetService, String endpointURI, final Class<T> entityClass){
this.endpointURI = endpointURI;
this.targetService = targetService;
this.entityClass = entityClass;
}
 void updateServers(Collection<Server> newServers) {
Set<Server> oldServers = clients.keySet();
if (oldServers.size() == newServers.size() && newServers.containsAll(oldServers))
{
return;
}
updateClients(newServers);
}

public  String getEndpointURI() {
return endpointURI;
}

private  void updateClients(Collection<Server> newServers) {
for (Server s : clients.keySet())
{
if (!newServers.contains(s))
{
clients.remove(s);
}
}
for (Server s : newServers)
{
clients.putIfAbsent(s, new  RESTClient<T>(s.getHost() + ":" + s.getPort() + "/" + targetService.getServiceName(), RESTClient.DEFAULT_REST_APPLICATION, endpointURI, entityClass));
}
}

 RESTClient<T> getRESTClient(Server server) {
return clients.get(server);
}

}
