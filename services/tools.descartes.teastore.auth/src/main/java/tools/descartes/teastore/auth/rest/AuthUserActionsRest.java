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
threadMonitoringController.enterService("_psR6QQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_psShUAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
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
threadMonitoringController.exitInternalAction("_psShUAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_psZPAwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
blob = new  ShaSecurityProvider().secure(blob);
threadMonitoringController.exitInternalAction("_psZPAwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_psZ2EAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949292008189610234949244 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_psZ2EAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949292008189610234949244;
}
finally {
threadMonitoringController.exitService("_psR6QQsxEey1ZNZ-8Luqpw");
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
threadMonitoringController.enterService("_pstYEAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_pst_IAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050694929300004596523230549265 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_pst_IAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050694929300004596523230549265;
}
finally {
threadMonitoringController.exitService("_pstYEAsxEey1ZNZ-8Luqpw");
}
}

@POST
@Path("logout")
public  Response logout(SessionBlob blob) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
threadMonitoringController.enterService("_ps92wAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_ps-d0AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
blob.setUID(null);
blob.setSID(null);
blob.setOrder(new  Order());
blob.getOrderItems().clear();
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492930018995903482229115 = Response.status(Response.Status.OK).entity(blob).build();
threadMonitoringController.exitInternalAction("_ps-d0AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492930018995903482229115;
}
finally {
threadMonitoringController.exitService("_ps92wAsxEey1ZNZ-8Luqpw");
}
}

@POST
@Path("isloggedin")
public  Response isLoggedIn(SessionBlob blob) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("blob", blob);
threadMonitoringController.enterService("_ptD9YAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_ptI14AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492940017911281294249448 = Response.status(Response.Status.OK).entity(new  ShaSecurityProvider().validate(blob)).build();
threadMonitoringController.exitInternalAction("_ptI14AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_ptI15QsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492940037781246963191173 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492940017911281294249448;
threadMonitoringController.exitInternalAction("_ptI15QsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_ptI15gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949294005106048802572065 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492940037781246963191173;
threadMonitoringController.exitInternalAction("_ptI15gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949294005106048802572065;
}
finally {
threadMonitoringController.exitService("_ptD9YAsxEey1ZNZ-8Luqpw");
}
}

}
