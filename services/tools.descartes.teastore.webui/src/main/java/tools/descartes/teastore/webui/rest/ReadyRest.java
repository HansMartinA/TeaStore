package tools.descartes.teastore.webui.rest;

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
threadMonitoringController.enterService("_yEgl4PhsEeuIqOQIQEQ0aQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_yEjCIPhsEeuIqOQIQEQ0aQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16472034342730022180905276042018 = Response.ok(String.valueOf(true)).build();
threadMonitoringController.exitInternalAction("_yEjCIPhsEeuIqOQIQEQ0aQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16472034342730022180905276042018;
}
finally {
threadMonitoringController.exitService("_yEgl4PhsEeuIqOQIQEQ0aQ");
}
}

}
