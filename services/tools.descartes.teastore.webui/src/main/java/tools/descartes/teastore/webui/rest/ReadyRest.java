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
threadMonitoringController.enterService("_LrNmQQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LrO0YAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185487005546661296668118 = Response.ok(String.valueOf(true)).build();
threadMonitoringController.exitInternalAction("_LrO0YAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185487005546661296668118;
}
finally {
threadMonitoringController.exitService("_LrNmQQskEeyH6q2UaRYLeA");
}
}

}
