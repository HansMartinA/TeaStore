package tools.descartes.teastore.persistence.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tools.descartes.teastore.persistence.repository.CacheManager;
@Path("cache")
@Produces("text/plain")
public final class CacheManagerEndpoint {
@DELETE
@Path("/class/{class}")
public  Response clearClassCache(@PathParam("class")
final String className) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("className", className);
threadMonitoringController.enterService("_2Um_kPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
boolean classfound = true;
try {
Class<?> entityClass = Class.forName(className);
CacheManager.MANAGER.clearLocalCacheOnly(entityClass);
}
catch(Exception e){
classfound = false;
}
if (classfound)
{
return Response.ok(className).build();
}
return Response.status(Status.NOT_FOUND).build();
}
finally {
threadMonitoringController.exitService("_2Um_kPhgEeudoMIzjEj1xQ");
}
}

@DELETE
@Path("/cache")
public  Response clearAllCaches() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_2WIpkPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
CacheManager.MANAGER.clearLocalCacheOnly();
return Response.ok("cleared").build();
}
finally {
threadMonitoringController.exitService("_2WIpkPhgEeudoMIzjEj1xQ");
}
}

@DELETE
@Path("/emf")
public  Response clearEMF() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_2Y1YQPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
CacheManager.MANAGER.resetLocalEMF();
return Response.ok("clearedEMF").build();
}
finally {
threadMonitoringController.exitService("_2Y1YQPhgEeudoMIzjEj1xQ");
}
}

}
