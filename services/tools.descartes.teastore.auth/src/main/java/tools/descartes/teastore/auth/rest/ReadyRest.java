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
threadMonitoringController.enterService("_uqUgQPkXEeuv6O-Z3paUUA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_uqW8gPkXEeuv6O-Z3paUUA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16472029616370047982827336103784 = Response.ok(String.valueOf(true)).build();
threadMonitoringController.exitInternalAction("_uqW8gPkXEeuv6O-Z3paUUA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16472029616370047982827336103784;
}
finally {
threadMonitoringController.exitService("_uqUgQPkXEeuv6O-Z3paUUA");
}
}

}
