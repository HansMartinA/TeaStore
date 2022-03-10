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
threadMonitoringController.enterService("_qJBgoAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qJDV1AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qJD88wsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean success = Registry.getRegistryInstance().register(name, location);
threadMonitoringController.exitInternalAction("_qJD88wsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qJDV1AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qJEj8AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
if (success)
{
return Response.status(Response.Status.OK).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949320007640890655156265 = Response.status(Response.Status.CONFLICT).build();
threadMonitoringController.exitInternalAction("_qJEj8AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949320007640890655156265;
}
finally {
threadMonitoringController.exitService("_qJBgoAsxEey1ZNZ-8Luqpw");
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
threadMonitoringController.enterService("_qJI1YAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qJKqkQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qJKqlgsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean success = Registry.getRegistryInstance().unregister(name, location);
threadMonitoringController.exitInternalAction("_qJKqlgsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qJKqkQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qJLRoAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
if (success)
{
return Response.status(Response.Status.OK).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493220015465176609936004 = Response.status(Response.Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_qJLRoAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493220015465176609936004;
}
finally {
threadMonitoringController.exitService("_qJI1YAsxEey1ZNZ-8Luqpw");
}
}

@GET
@Path("{name}")
public  Response getInstances(@PathParam("name")
final String name) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("name", name);
threadMonitoringController.enterService("_qJPjEAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qJQxNAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qJQxPQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
List<String> locations = Registry.getRegistryInstance().getLocations(name);
threadMonitoringController.exitInternalAction("_qJQxPQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qJQxNAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qJRYQAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493230014212252849173024 = Response.status(Response.Status.OK).entity(locations).build();
threadMonitoringController.exitInternalAction("_qJRYQAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493230014212252849173024;
}
finally {
threadMonitoringController.exitService("_qJPjEAsxEey1ZNZ-8Luqpw");
}
}

}
