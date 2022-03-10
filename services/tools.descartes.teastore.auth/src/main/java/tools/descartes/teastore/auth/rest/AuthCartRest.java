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
threadMonitoringController.enterService("_po-uoAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_ppJtwAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
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
threadMonitoringController.exitInternalAction("_ppJtwAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_ppSQoAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
blob = new  ShaSecurityProvider().secure(blob);
threadMonitoringController.exitInternalAction("_ppSQoAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_ppS3sAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492890018772486948423384 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_ppS3sAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492890018772486948423384;
}
finally {
threadMonitoringController.exitService("_po-uoAsxEey1ZNZ-8Luqpw");
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
threadMonitoringController.enterService("_pr3qkQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_pr4RoAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949291007155398955814389 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_pr4RoAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949291007155398955814389;
}
else
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949291005416947069248922 = Response.status(Response.Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_pr4RoAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949291005416947069248922;
}
}
finally {
threadMonitoringController.exitService("_pr3qkQsxEey1ZNZ-8Luqpw");
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
threadMonitoringController.enterService("_psBbkQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_psFtAAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
for (OrderItem item : blob.getOrderItems())
{
if (item.getProductId() == pid)
{
item.setQuantity(quantity);
blob = new  ShaSecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050694929100985175940661075 = Response.status(Response.Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_psFtAAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050694929100985175940661075;
}
finally {
threadMonitoringController.exitService("_psBbkQsxEey1ZNZ-8Luqpw");
}
}

}
