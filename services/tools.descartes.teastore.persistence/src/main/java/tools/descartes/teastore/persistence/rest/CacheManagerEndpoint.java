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
threadMonitoringController.enterService("_8j1W8Qd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8j2lEAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789249005874094821854529 = Response.status(Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_8j2lEAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789249005874094821854529;
}
finally {
threadMonitoringController.exitService("_8j1W8Qd_Eeyp7eds7yI7tA");
}
}

@DELETE
@Path("/cache")
public  Response clearAllCaches() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_8j_vAAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8kCyWQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
CacheManager.MANAGER.clearLocalCacheOnly();
threadMonitoringController.exitInternalAction("_8kCyWQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kCyWgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789250003327631432254814 = Response.ok("cleared").build();
threadMonitoringController.exitInternalAction("_8kCyWgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789250003327631432254814;
}
finally {
threadMonitoringController.exitService("_8j_vAAd_Eeyp7eds7yI7tA");
}
}

@DELETE
@Path("/emf")
public  Response clearEMF() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_8kKuIQd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8kMjVwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
CacheManager.MANAGER.resetLocalEMF();
threadMonitoringController.exitInternalAction("_8kMjVwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kNKYAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789250008679857400899954 = Response.ok("clearedEMF").build();
threadMonitoringController.exitInternalAction("_8kNKYAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789250008679857400899954;
}
finally {
threadMonitoringController.exitService("_8kKuIQd_Eeyp7eds7yI7tA");
}
}

}
