package tools.descartes.teastore.auth.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.auth.security.ShaSecurityProvider;
import tools.descartes.teastore.entities.OrderItem;
import tools.descartes.teastore.entities.Product;
import tools.descartes.teastore.entities.message.SessionBlob;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.rest.LoadBalancedCRUDOperations;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.registryclient.util.TimeoutException;
@Path("cart")
@Produces({"application/json"})
@Consumes({"application/json"})
public class AuthCartRest {
@POST
@Path("add/{pid}")
public  Response addProductToCart(SessionBlob blob, @PathParam("pid")
final Long pid) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
monitoringServiceParameters.addValue("pid", pid);
threadMonitoringController.enterService("_yycgYPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
Product product;
try {
product = LoadBalancedCRUDOperations.getEntity(Service.PERSISTENCE, "products", Product.class, pid);
}
catch(TimeoutException e){
return Response.status(408).build();
}
catch(NotFoundException e){
return Response.status(404).build();
}
for (OrderItem orderItem : blob.getOrderItems())
{
if (orderItem.getProductId() == pid)
{
orderItem.setQuantity(orderItem.getQuantity() + 1);
blob = new  ShaSecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
}
OrderItem item = new  OrderItem();
item.setProductId(pid);
item.setQuantity(1);
item.setUnitPriceInCents(product.getListPriceInCents());
blob.getOrderItems().add(item);
blob = new  ShaSecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
finally {
threadMonitoringController.exitService("_yycgYPhgEeudoMIzjEj1xQ");
}
}

@POST
@Path("remove/{pid}")
public  Response removeProductFromCart(SessionBlob blob, @PathParam("pid")
final Long pid) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
monitoringServiceParameters.addValue("pid", pid);
threadMonitoringController.enterService("_03_3oPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
OrderItem toRemove = null;
for (OrderItem item : blob.getOrderItems())
{
if (item.getProductId() == pid)
{
toRemove = item;
}
}
if (toRemove != null)
{
blob.getOrderItems().remove(toRemove);
blob = new  ShaSecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
else
{
return Response.status(Response.Status.NOT_FOUND).build();
}
}
finally {
threadMonitoringController.exitService("_03_3oPhgEeudoMIzjEj1xQ");
}
}

@PUT
@Path("{pid}")
public  Response updateQuantity(SessionBlob blob, @PathParam("pid")
final Long pid, @QueryParam("quantity")
int quantity) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
monitoringServiceParameters.addValue("pid", pid);
monitoringServiceParameters.addValue("quantity", quantity);
threadMonitoringController.enterService("_09v6UPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
for (OrderItem item : blob.getOrderItems())
{
if (item.getProductId() == pid)
{
item.setQuantity(quantity);
blob = new  ShaSecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
}
return Response.status(Response.Status.NOT_FOUND).build();
}
finally {
threadMonitoringController.exitService("_09v6UPhgEeudoMIzjEj1xQ");
}
}

}
