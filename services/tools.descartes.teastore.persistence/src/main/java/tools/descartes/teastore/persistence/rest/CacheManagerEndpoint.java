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
threadMonitoringController.enterInternalAction("_2UxXoPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666270008998044771450011 = Response.status(Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_2UxXoPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666270008998044771450011;
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
threadMonitoringController.enterInternalAction("_2WX6KfhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
CacheManager.MANAGER.clearLocalCacheOnly();
threadMonitoringController.exitInternalAction("_2WX6KfhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2Wen0PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666273001427426221222946 = Response.ok("cleared").build();
threadMonitoringController.exitInternalAction("_2Wen0PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666273001427426221222946;
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
threadMonitoringController.enterInternalAction("_2ZeRePhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
CacheManager.MANAGER.resetLocalEMF();
threadMonitoringController.exitInternalAction("_2ZeRePhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2Z7kcPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666275005806090319276994 = Response.ok("clearedEMF").build();
threadMonitoringController.exitInternalAction("_2Z7kcPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666275005806090319276994;
}
finally {
threadMonitoringController.exitService("_2Y1YQPhgEeudoMIzjEj1xQ");
}
}

}
