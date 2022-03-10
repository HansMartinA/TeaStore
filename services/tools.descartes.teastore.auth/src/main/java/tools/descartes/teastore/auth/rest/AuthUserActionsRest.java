package tools.descartes.teastore.auth.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
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
threadMonitoringController.enterService("_MtBc4ApFEeyde98rBgRSjw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_MtD5IApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
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
threadMonitoringController.exitInternalAction("_MtD5IApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_MtbslApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
blob = new  ShaSecurityProvider().secure(blob);
threadMonitoringController.exitInternalAction("_MtbslApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Mtc6sApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306200007571264194531118 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_Mtc6sApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306200007571264194531118;
}
finally {
threadMonitoringController.exitService("_MtBc4ApFEeyde98rBgRSjw");
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
threadMonitoringController.enterService("_Mty48ApFEeyde98rBgRSjw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_MtzgAApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
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
blob.setSID(new  RandomSessionIdGenerator().getSessionId());
blob = new  ShaSecurityProvider().secure(blob);
return Response.status(Response.Status.OK).entity(blob).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306202009074869170572886 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_MtzgAApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306202009074869170572886;
}
finally {
threadMonitoringController.exitService("_Mty48ApFEeyde98rBgRSjw");
}
}

@POST
@Path("logout")
public  Response logout(SessionBlob blob) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
threadMonitoringController.enterService("_MuWSkApFEeyde98rBgRSjw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_MuXgsApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
blob.setUID(null);
blob.setSID(null);
blob.setOrder(new  Order());
blob.getOrderItems().clear();
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306203009090731637567934 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_MuXgsApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306203009090731637567934;
}
finally {
threadMonitoringController.exitService("_MuWSkApFEeyde98rBgRSjw");
}
}

@POST
@Path("isloggedin")
public  Response isLoggedIn(SessionBlob blob) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
threadMonitoringController.enterService("_MujG4ApFEeyde98rBgRSjw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_MuxJUApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306205003914596867677408 = Response.status(Response.Status.OK).entity(new  ShaSecurityProvider().validate(blob)).build();
threadMonitoringController.exitInternalAction("_MuxJUApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_MuxwZApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16304053062050008110086507829073 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306205003914596867677408;
threadMonitoringController.exitInternalAction("_MuxwZApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_MuyXcApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306205009087391318629731 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16304053062050008110086507829073;
threadMonitoringController.exitInternalAction("_MuyXcApFEeyde98rBgRSjw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630405306205009087391318629731;
}
finally {
threadMonitoringController.exitService("_MujG4ApFEeyde98rBgRSjw");
}
}

}
