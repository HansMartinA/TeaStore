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
boolean success = Registry.getRegistryInstance().register(name, location);
if (success)
{
return Response.status(Response.Status.OK).build();
}
return Response.status(Response.Status.CONFLICT).build();
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
boolean success = Registry.getRegistryInstance().unregister(name, location);
if (success)
{
return Response.status(Response.Status.OK).build();
}
return Response.status(Response.Status.NOT_FOUND).build();
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
List<String> locations = Registry.getRegistryInstance().getLocations(name);
return Response.status(Response.Status.OK).entity(locations).build();
}
finally {
threadMonitoringController.exitService("_8ur8EAd_Eeyp7eds7yI7tA");
}
}

}
