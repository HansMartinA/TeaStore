package tools.descartes.teastore.auth.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
@Path("ready")
@Produces({"application/json"})
public class ReadyRest {
@GET
@Path("isready")
public  Response isReady() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_yEL1wPhsEeuIqOQIQEQ0aQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_yEW04PhsEeuIqOQIQEQ0aQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647203434270009144107646906869 = Response.ok(String.valueOf(true)).build();
threadMonitoringController.exitInternalAction("_yEW04PhsEeuIqOQIQEQ0aQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647203434270009144107646906869;
}
finally {
threadMonitoringController.exitService("_yEL1wPhsEeuIqOQIQEQ0aQ");
}
}

}
