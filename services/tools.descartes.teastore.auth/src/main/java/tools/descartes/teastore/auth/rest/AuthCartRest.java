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
threadMonitoringController.enterService("_UPvyUA3FEeyW9oGRo1qexg", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_UQBfIA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
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
return Response.status(Response.Status.OK).entity(blob).build();
}
}
OrderItem item = new  OrderItem();
item.setProductId(pid);
item.setQuantity(1);
item.setUnitPriceInCents(product.getListPriceInCents());
blob.getOrderItems().add(item);
threadMonitoringController.exitInternalAction("_UQBfIA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_UQXdYw3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
blob = new  ShaSecurityProvider().secure(blob);
threadMonitoringController.exitInternalAction("_UQXdYw3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_UQYrgA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163079018273800016635200650214954 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_UQYrgA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163079018273800016635200650214954;
}
finally {
threadMonitoringController.exitService("_UPvyUA3FEeyW9oGRo1qexg");
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
threadMonitoringController.enterService("_UUERoA3FEeyW9oGRo1qexg", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_UUE4sA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182738009825051436389155 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_UUE4sA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182738009825051436389155;
}
else
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182738007890092318067818 = Response.status(Response.Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_UUE4sA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182738007890092318067818;
}
}
finally {
threadMonitoringController.exitService("_UUERoA3FEeyW9oGRo1qexg");
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
threadMonitoringController.enterService("_UUTiMA3FEeyW9oGRo1qexg", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_UUZo0A3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
for (OrderItem item : blob.getOrderItems())
{
if (item.getProductId() == pid)
{
item.setQuantity(quantity);
blob = new  ShaSecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182739004103204198048318 = Response.status(Response.Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_UUZo0A3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182739004103204198048318;
}
finally {
threadMonitoringController.exitService("_UUTiMA3FEeyW9oGRo1qexg");
}
}

}
