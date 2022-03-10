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
threadMonitoringController.enterService("_p_zTMQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_p_z6QAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492980012345690282551347 = Response.status(Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_p_z6QAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492980012345690282551347;
}
finally {
threadMonitoringController.exitService("_p_zTMQsxEey1ZNZ-8Luqpw");
}
}

@DELETE
@Path("/cache")
public  Response clearAllCaches() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_p_6A4QsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_p_8dIgsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
CacheManager.MANAGER.clearLocalCacheOnly();
threadMonitoringController.exitInternalAction("_p_8dIgsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_p_8dIwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949299007276867296009019 = Response.ok("cleared").build();
threadMonitoringController.exitInternalAction("_p_8dIwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949299007276867296009019;
}
finally {
threadMonitoringController.exitService("_p_6A4QsxEey1ZNZ-8Luqpw");
}
}

@DELETE
@Path("/emf")
public  Response clearEMF() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_qAG1MQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qAIDVwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
CacheManager.MANAGER.resetLocalEMF();
threadMonitoringController.exitInternalAction("_qAIDVwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qAIDWAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492990043241771193659817 = Response.ok("clearedEMF").build();
threadMonitoringController.exitInternalAction("_qAIDWAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492990043241771193659817;
}
finally {
threadMonitoringController.exitService("_qAG1MQsxEey1ZNZ-8Luqpw");
}
}

}
