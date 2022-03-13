package tools.descartes.teastore.registryclient.rest;

import java.util.List;
import java.util.Optional;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
import tools.descartes.teastore.registryclient.util.NotFoundException;
public final class LoadBalancedCRUDOperations {
private  LoadBalancedCRUDOperations(){
}
public static <T>  long sendEntityForCreation(Service service, String endpointURI, Class<T> entityClass, T entity)throws NotFoundException, LoadBalancerTimeoutException {
return Optional.ofNullable(ServiceLoadBalancer.loadBalanceRESTOperation(service, endpointURI, entityClass, client -> NonBalancedCRUDOperations.sendEntityForCreation(client, entity))).orElse( - 1L);
}

public static <T>  boolean sendEntityForUpdate(Service service, String endpointURI, Class<T> entityClass, long id, T entity)throws NotFoundException, LoadBalancerTimeoutException {
return Optional.ofNullable(ServiceLoadBalancer.loadBalanceRESTOperation(service, endpointURI, entityClass, client -> NonBalancedCRUDOperations.sendEntityForUpdate(client, id, entity))).orElse(false);
}

public static <T>  boolean deleteEntity(Service service, String endpointURI, Class<T> entityClass, long id)throws NotFoundException, LoadBalancerTimeoutException {
return Optional.ofNullable(ServiceLoadBalancer.loadBalanceRESTOperation(service, endpointURI, entityClass, client -> NonBalancedCRUDOperations.deleteEntity(client, id))).orElse(false);
}

public static <T>  T getEntity(Service service, String endpointURI, Class<T> entityClass, long id)throws NotFoundException, LoadBalancerTimeoutException {
return ServiceLoadBalancer.loadBalanceRESTOperation(service, endpointURI, entityClass, client -> NonBalancedCRUDOperations.getEntity(client, id));
}

public static <T>  T getEntityWithProperties(Service service, String endpointURI, Class<T> entityClass, String propertyName, String propertyValue)throws NotFoundException, LoadBalancerTimeoutException {
return ServiceLoadBalancer.loadBalanceRESTOperation(service, endpointURI, entityClass, client -> NonBalancedCRUDOperations.getEntityWithProperty(client, propertyName, propertyValue));
}

public static <T>  List<T> getEntities(Service service, String endpointURI, Class<T> entityClass, int startIndex, int limit)throws NotFoundException, LoadBalancerTimeoutException {
return ServiceLoadBalancer.loadBalanceRESTOperation(service, endpointURI, entityClass, client -> NonBalancedCRUDOperations.getEntities(client, startIndex, limit));
}

public static <T>  List<T> getEntities(Service service, String endpointURI, Class<T> entityClass, String filterURI, long filterId, int startIndex, int limit)throws NotFoundException, LoadBalancerTimeoutException {
return ServiceLoadBalancer.loadBalanceRESTOperation(service, endpointURI, entityClass, client -> NonBalancedCRUDOperations.getEntities(client, filterURI, filterId, startIndex, limit));
}

}
