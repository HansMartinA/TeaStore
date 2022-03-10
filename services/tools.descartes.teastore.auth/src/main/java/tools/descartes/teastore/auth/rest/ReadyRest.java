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
threadMonitoringController.enterService("_LO2aYQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LO3ogAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185459003795063556226296 = Response.ok(String.valueOf(true)).build();
threadMonitoringController.exitInternalAction("_LO3ogAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185459003795063556226296;
}
finally {
threadMonitoringController.exitService("_LO2aYQskEeyH6q2UaRYLeA");
}
}

}
