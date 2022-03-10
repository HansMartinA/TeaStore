package tools.descartes.teastore.registry.rest;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
@Path("services")
@Produces({"application/json"})
public class RegistryREST {
@PUT
@Path("{name}/{location}")
public  Response register(@PathParam("name")
final String name, @PathParam("location")
final String location) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("name", name);
monitoringServiceParameters.addValue("location", location);
threadMonitoringController.enterService("_8uYaEAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8udSlAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8uegugd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean success = Registry.getRegistryInstance().register(name, location);
threadMonitoringController.exitInternalAction("_8uegugd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8udSlAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8ufHwAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (success)
{
return Response.status(Response.Status.OK).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078927200009001701496945902 = Response.status(Response.Status.CONFLICT).build();
threadMonitoringController.exitInternalAction("_8ufHwAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078927200009001701496945902;
}
finally {
threadMonitoringController.exitService("_8uYaEAd_Eeyp7eds7yI7tA");
}
}

@DELETE
@Path("{name}/{location}")
public  Response unregister(@PathParam("name")
final String name, @PathParam("location")
final String location) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("name", name);
monitoringServiceParameters.addValue("location", location);
threadMonitoringController.enterService("_8ujZMAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8uknVAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8ulOZAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean success = Registry.getRegistryInstance().unregister(name, location);
threadMonitoringController.exitInternalAction("_8ulOZAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8uknVAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8ul1cAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (success)
{
return Response.status(Response.Status.OK).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789273008013521741699761 = Response.status(Response.Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_8ul1cAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789273008013521741699761;
}
finally {
threadMonitoringController.exitService("_8ujZMAd_Eeyp7eds7yI7tA");
}
}

@GET
@Path("{name}")
public  Response getInstances(@PathParam("name")
final String name) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("name", name);
threadMonitoringController.enterService("_8ur8EAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8uuYUgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8uuYWwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
List<String> locations = Registry.getRegistryInstance().getLocations(name);
threadMonitoringController.exitInternalAction("_8uuYWwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8uuYUgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8uvmcAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892740007867334270589754 = Response.status(Response.Status.OK).entity(locations).build();
threadMonitoringController.exitInternalAction("_8uvmcAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892740007867334270589754;
}
finally {
threadMonitoringController.exitService("_8ur8EAd_Eeyp7eds7yI7tA");
}
}

}
