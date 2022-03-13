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
threadMonitoringController.enterService("_uspbgPkXEeuv6O-Z3paUUA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_usrQsPkXEeuv6O-Z3paUUA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16472029616390026905454198100975 = Response.ok(String.valueOf(true)).build();
threadMonitoringController.exitInternalAction("_usrQsPkXEeuv6O-Z3paUUA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16472029616390026905454198100975;
}
finally {
threadMonitoringController.exitService("_uspbgPkXEeuv6O-Z3paUUA");
}
}

}
