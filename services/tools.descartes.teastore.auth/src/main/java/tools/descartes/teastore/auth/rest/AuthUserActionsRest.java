package tools.descartes.teastore.auth.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.auth.security.BCryptProvider;
import tools.descartes.teastore.auth.security.RandomSessionIdGenerator;
import tools.descartes.teastore.auth.security.ShaSecurityProvider;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.OrderItem;
import tools.descartes.teastore.entities.User;
import tools.descartes.teastore.entities.message.SessionBlob;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.rest.LoadBalancedCRUDOperations;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.registryclient.util.TimeoutException;
@Path("useractions")
@Produces({"application/json"})
@Consumes({"application/json"})
public class AuthUserActionsRest {
@POST
@Path("placeorder")
public  Response placeOrder(SessionBlob blob, @QueryParam("totalPriceInCents")
long totalPriceInCents, @QueryParam("addressName")
String addressName, @QueryParam("address1")
String address1, @QueryParam("address2")
String address2, @QueryParam("creditCardCompany")
String creditCardCompany, @QueryParam("creditCardNumber")
String creditCardNumber, @QueryParam("creditCardExpiryDate")
String creditCardExpiryDate) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
monitoringServiceParameters.addValue("totalPriceInCents", totalPriceInCents);
monitoringServiceParameters.addValue("addressName", addressName);
monitoringServiceParameters.addValue("address1", address1);
monitoringServiceParameters.addValue("address2", address2);
monitoringServiceParameters.addValue("creditCardCompany", creditCardCompany);
monitoringServiceParameters.addValue("creditCardNumber", creditCardNumber);
monitoringServiceParameters.addValue("creditCardExpiryDate", creditCardExpiryDate);
threadMonitoringController.enterService("_UVU14A3FEeyW9oGRo1qexg", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_UVWEAA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
if (new  ShaSecurityProvider().validate(blob) == null || blob.getOrderItems().isEmpty())
{
return Response.status(Response.Status.NOT_FOUND).build();
}
blob.getOrder().setUserId(blob.getUID());
blob.getOrder().setTotalPriceInCents(totalPriceInCents);
blob.getOrder().setAddressName(addressName);
blob.getOrder().setAddress1(address1);
blob.getOrder().setAddress2(address2);
blob.getOrder().setCreditCardCompany(creditCardCompany);
blob.getOrder().setCreditCardExpiryDate(creditCardExpiryDate);
blob.getOrder().setCreditCardNumber(creditCardNumber);
blob.getOrder().setTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
long orderId;
try {
orderId = LoadBalancedCRUDOperations.sendEntityForCreation(Service.PERSISTENCE, "orders", Order.class, blob.getOrder());
}
catch(LoadBalancerTimeoutException e){
return Response.status(408).build();
}
catch(NotFoundException e){
return Response.status(404).build();
}
for (OrderItem item : blob.getOrderItems())
{
try {
item.setOrderId(orderId);
LoadBalancedCRUDOperations.sendEntityForCreation(Service.PERSISTENCE, "orderitems", OrderItem.class, item);
}
catch(TimeoutException e){
return Response.status(408).build();
}
catch(NotFoundException e){
return Response.status(404).build();
}
}
blob.setOrder(new  Order());
blob.getOrderItems().clear();
threadMonitoringController.exitInternalAction("_UVWEAA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_UVkthA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
blob = new  ShaSecurityProvider().secure(blob);
threadMonitoringController.exitInternalAction("_UVkthA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_UVmisA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16307901827410042272336456826065 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_UVmisA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16307901827410042272336456826065;
}
finally {
threadMonitoringController.exitService("_UVU14A3FEeyW9oGRo1qexg");
}
}

@POST
@Path("login")
public  Response login(SessionBlob blob, @QueryParam("name")
String name, @QueryParam("password")
String password) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
monitoringServiceParameters.addValue("name", name);
monitoringServiceParameters.addValue("password", password);
threadMonitoringController.enterService("_UV1MMQ3FEeyW9oGRo1qexg", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_UV1zQA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
User user;
try {
user = LoadBalancedCRUDOperations.getEntityWithProperties(Service.PERSISTENCE, "users", User.class, "name", name);
}
catch(TimeoutException e){
return Response.status(408).build();
}
catch(NotFoundException e){
return Response.status(Response.Status.OK).entity(blob).build();
}
if (user != null && BCryptProvider.checkPassword(password, user.getPassword()))
{
blob.setUID(user.getId());
blob.setSID(new  RandomSessionIdGenerator().getSessionId());
blob = new  ShaSecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182742008386192561626922 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_UV1zQA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182742008386192561626922;
}
finally {
threadMonitoringController.exitService("_UV1MMQ3FEeyW9oGRo1qexg");
}
}

@POST
@Path("logout")
public  Response logout(SessionBlob blob) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
threadMonitoringController.enterService("_UWFq4A3FEeyW9oGRo1qexg", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_UWGR8A3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
blob.setUID(null);
blob.setSID(null);
blob.setOrder(new  Order());
blob.getOrderItems().clear();
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182743005590966943347139 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_UWGR8A3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182743005590966943347139;
}
finally {
threadMonitoringController.exitService("_UWFq4A3FEeyW9oGRo1qexg");
}
}

@POST
@Path("isloggedin")
public  Response isLoggedIn(SessionBlob blob) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
threadMonitoringController.enterService("_UWKjYA3FEeyW9oGRo1qexg", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_UWQC8A3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16307901827440016943297056588014 = Response.status(Response.Status.OK).entity(new  ShaSecurityProvider().validate(blob)).build();
threadMonitoringController.exitInternalAction("_UWQC8A3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_UWQC9Q3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182745001925877226031607 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16307901827440016943297056588014;
threadMonitoringController.exitInternalAction("_UWQC9Q3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_UWQqAA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16307901827450021832316559481568 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630790182745001925877226031607;
threadMonitoringController.exitInternalAction("_UWQqAA3FEeyW9oGRo1qexg", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16307901827450021832316559481568;
}
finally {
threadMonitoringController.exitService("_UWKjYA3FEeyW9oGRo1qexg");
}
}

}
