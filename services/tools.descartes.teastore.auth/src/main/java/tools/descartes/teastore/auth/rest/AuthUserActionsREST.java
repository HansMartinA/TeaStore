package tools.descartes.teastore.auth.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.OrderItem;
import tools.descartes.teastore.entities.User;
import tools.descartes.teastore.entities.message.SessionBlob;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.rest.LoadBalancedCRUDOperations;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.registryclient.util.TimeoutException;
import tools.descartes.teastore.auth.security.BCryptProvider;
import tools.descartes.teastore.auth.security.RandomSessionIdGenerator;
import tools.descartes.teastore.auth.security.SHASecurityProvider;
@Path("useractions")
@Produces({"application/json"})
@Consumes({"application/json"})
public class AuthUserActionsREST {
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
threadMonitoringController.enterService("_8OgAIAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8OgnMAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (new  SHASecurityProvider().validate(blob) == null || blob.getOrderItems().isEmpty())
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
threadMonitoringController.exitInternalAction("_8OgnMAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8OnU4Qd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
blob = new  SHASecurityProvider().secure(blob);
threadMonitoringController.exitInternalAction("_8OnU4Qd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8OnU4gd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789241004038921955142303 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_8OnU4gd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789241004038921955142303;
}
finally {
threadMonitoringController.exitService("_8OgAIAd_Eeyp7eds7yI7tA");
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
threadMonitoringController.enterService("_8O8sEAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8O9TIAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
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
long tic = System.currentTimeMillis();
while (System.currentTimeMillis() - tic < 1500)
{
}
if (user != null)
{
blob.setUID(user.getId());
blob.setSID(new  RandomSessionIdGenerator().getSessionID());
blob = new  SHASecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789241005209901264986425 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_8O9TIAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789241005209901264986425;
}
finally {
threadMonitoringController.exitService("_8O8sEAd_Eeyp7eds7yI7tA");
}
}

@POST
@Path("logout")
public  Response logout(SessionBlob blob) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
threadMonitoringController.enterService("_8PNKwAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8PNx0Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
blob.setUID(null);
blob.setSID(null);
blob.setOrder(new  Order());
blob.getOrderItems().clear();
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789242008472932705073294 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_8PNx0Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789242008472932705073294;
}
finally {
threadMonitoringController.exitService("_8PNKwAd_Eeyp7eds7yI7tA");
}
}

@POST
@Path("isloggedin")
public  Response isLoggedIn(SessionBlob blob) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
threadMonitoringController.enterService("_8PSDQAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8PVtoAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789243008600775205542597 = Response.status(Response.Status.OK).entity(new  SHASecurityProvider().validate(blob)).build();
threadMonitoringController.exitInternalAction("_8PVtoAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8PVtpQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789243005672292336003045 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789243008600775205542597;
threadMonitoringController.exitInternalAction("_8PVtpQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8PVtpgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892430020916212066236461 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789243005672292336003045;
threadMonitoringController.exitInternalAction("_8PVtpgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892430020916212066236461;
}
finally {
threadMonitoringController.exitService("_8PSDQAd_Eeyp7eds7yI7tA");
}
}

}
