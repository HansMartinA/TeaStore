package tools.descartes.teastore.registryclient.loadbalancers;

import java.util.function.Function;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.registryclient.util.RESTClient;
import tools.descartes.teastore.registryclient.util.TimeoutException;
final class ServiceLoadBalancerResult<R>  {
private int statusCode = 200;

private R entity = null;

private  ServiceLoadBalancerResult(){
}
static <T, R>  ServiceLoadBalancerResult<R> fromRESTOperation(RESTClient<T> client, Function<RESTClient<T>, R> operation)throws NotFoundException, TimeoutException {
ServiceLoadBalancerResult<R> result = new  ServiceLoadBalancerResult<>();
try {
result.setEntity(operation.apply(client));
}
catch(NotFoundException e){
result.setStatusCode(NotFoundException.ERROR_CODE);
}
catch(TimeoutException e){
result.setStatusCode(TimeoutException.ERROR_CODE);
}
return result;
}

public  int getStatusCode() {
return statusCode;
}

private  void setStatusCode(int statusCode) {
this.statusCode = statusCode;
}

public  R getEntity() {
return entity;
}

private  void setEntity(R entity) {
this.entity = entity;
}

}
