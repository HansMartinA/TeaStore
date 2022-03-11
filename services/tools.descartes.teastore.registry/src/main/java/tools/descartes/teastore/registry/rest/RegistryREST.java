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
threadMonitoringController.enterService("_5tDiEPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_58ly5PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_5824qvhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean success = Registry.getRegistryInstance().register(name, location);
threadMonitoringController.exitInternalAction("_5824qvhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_58ly5PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_59GwQPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
if (success)
{
return Response.status(Response.Status.OK).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666323005121805047943824 = Response.status(Response.Status.CONFLICT).build();
threadMonitoringController.exitInternalAction("_59GwQPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666323005121805047943824;
}
finally {
threadMonitoringController.exitService("_5tDiEPhgEeudoMIzjEj1xQ");
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
threadMonitoringController.enterService("_6JMEcPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_6Jfmc_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_6JpXcPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean success = Registry.getRegistryInstance().unregister(name, location);
threadMonitoringController.exitInternalAction("_6JpXcPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_6Jfmc_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_6KZlYPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
if (success)
{
return Response.status(Response.Status.OK).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666325007136290467701167 = Response.status(Response.Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_6KZlYPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666325007136290467701167;
}
finally {
threadMonitoringController.exitService("_6JMEcPhgEeudoMIzjEj1xQ");
}
}

@GET
@Path("{name}")
public  Response getInstances(@PathParam("name")
final String name) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("name", name);
threadMonitoringController.enterService("_6VpMEPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_6XEIZPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_6YAjmPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
List<String> locations = Registry.getRegistryInstance().getLocations(name);
threadMonitoringController.exitInternalAction("_6YAjmPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_6XEIZPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_6YV6wPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666328003714439338732568 = Response.status(Response.Status.OK).entity(locations).build();
threadMonitoringController.exitInternalAction("_6YV6wPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666328003714439338732568;
}
finally {
threadMonitoringController.exitService("_6VpMEPhgEeudoMIzjEj1xQ");
}
}

}
