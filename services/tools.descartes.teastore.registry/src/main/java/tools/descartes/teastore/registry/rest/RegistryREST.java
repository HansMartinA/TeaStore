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
threadMonitoringController.enterService("_LqsB0QskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LqueFAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LqvFLAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean success = Registry.getRegistryInstance().register(name, location);
threadMonitoringController.exitInternalAction("_LqvFLAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_LqueFAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LqvsMAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (success)
{
return Response.status(Response.Status.OK).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854870019014491292955094 = Response.status(Response.Status.CONFLICT).build();
threadMonitoringController.exitInternalAction("_LqvsMAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854870019014491292955094;
}
finally {
threadMonitoringController.exitService("_LqsB0QskEeyH6q2UaRYLeA");
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
threadMonitoringController.enterService("_LqyvgQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_Lqz9pAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Lqz9qQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean success = Registry.getRegistryInstance().unregister(name, location);
threadMonitoringController.exitInternalAction("_Lqz9qQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_Lqz9pAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Lq0ksAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (success)
{
return Response.status(Response.Status.OK).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118548700940606362314162 = Response.status(Response.Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_Lq0ksAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118548700940606362314162;
}
finally {
threadMonitoringController.exitService("_LqyvgQskEeyH6q2UaRYLeA");
}
}

@GET
@Path("{name}")
public  Response getInstances(@PathParam("name")
final String name) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("name", name);
threadMonitoringController.enterService("_Lq3oAQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_Lq42JAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Lq42LQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
List<String> locations = Registry.getRegistryInstance().getLocations(name);
threadMonitoringController.exitInternalAction("_Lq42LQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_Lq42JAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Lq5dMAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185487006168826661076477 = Response.status(Response.Status.OK).entity(locations).build();
threadMonitoringController.exitInternalAction("_Lq5dMAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185487006168826661076477;
}
finally {
threadMonitoringController.exitService("_Lq3oAQskEeyH6q2UaRYLeA");
}
}

}
