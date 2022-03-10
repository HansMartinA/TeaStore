package tools.descartes.teastore.registryclient.rest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.entities.Product;
import tools.descartes.teastore.entities.message.SessionBlob;
public final class LoadBalancedStoreOperations {
private  LoadBalancedStoreOperations(){
}
public static  SessionBlob placeOrder(SessionBlob blob, String addressName, String address1, String address2, String creditCardCompany, String creditCardExpiryDate, long totalPriceInCents, String creditCardNumber)throws NotFoundException, LoadBalancerTimeoutException {
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.AUTH, "useractions", Product.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("placeorder").queryParam("addressName", addressName).queryParam("address1", address1).queryParam("address2", address2).queryParam("creditCardCompany", creditCardCompany).queryParam("creditCardNumber", creditCardNumber).queryParam("creditCardExpiryDate", creditCardExpiryDate).queryParam("totalPriceInCents", totalPriceInCents)).post(Entity.entity(blob, MediaType.APPLICATION_JSON), Response.class)));
return RestUtil.readThrowAndOrClose(r, SessionBlob.class);
}

public static  SessionBlob login(SessionBlob blob, String name, String password)throws NotFoundException, LoadBalancerTimeoutException {
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.AUTH, "useractions", Product.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("login").queryParam("name", name).queryParam("password", password)).post(Entity.entity(blob, MediaType.APPLICATION_JSON), Response.class)));
return RestUtil.readThrowAndOrClose(r, SessionBlob.class);
}

public static  SessionBlob logout(SessionBlob blob)throws NotFoundException, LoadBalancerTimeoutException {
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.AUTH, "useractions", Product.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("logout")).post(Entity.entity(blob, MediaType.APPLICATION_JSON), Response.class)));
return RestUtil.readThrowAndOrClose(r, SessionBlob.class);
}

public static  boolean isLoggedIn(SessionBlob blob)throws NotFoundException, LoadBalancerTimeoutException {
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.AUTH, "useractions", Product.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("isloggedin")).post(Entity.entity(blob, MediaType.APPLICATION_JSON), Response.class)));
return RestUtil.readThrowAndOrClose(r, SessionBlob.class) != null;
}

public static  SessionBlob addProductToCart(SessionBlob blob, long pid)throws NotFoundException, LoadBalancerTimeoutException {
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.AUTH, "cart", Product.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("add").path("" + pid)).post(Entity.entity(blob, MediaType.APPLICATION_JSON), Response.class)));
return RestUtil.readThrowAndOrClose(r, SessionBlob.class);
}

public static  SessionBlob removeProductFromCart(SessionBlob blob, long pid)throws NotFoundException, LoadBalancerTimeoutException {
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.AUTH, "cart", Product.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("remove").path("" + pid)).post(Entity.entity(blob, MediaType.APPLICATION_JSON), Response.class)));
return RestUtil.readThrowAndOrClose(r, SessionBlob.class);
}

public static  SessionBlob updateQuantity(SessionBlob blob, long pid, int quantity)throws NotFoundException, LoadBalancerTimeoutException {
if (quantity < 1)
{
throw new  IllegalArgumentException("Quantity has to be larger than 1");
}
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.AUTH, "cart", Product.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("" + pid).queryParam("quantity", quantity)).put(Entity.entity(blob, MediaType.APPLICATION_JSON), Response.class)));
return RestUtil.readThrowAndOrClose(r, SessionBlob.class);
}

}
