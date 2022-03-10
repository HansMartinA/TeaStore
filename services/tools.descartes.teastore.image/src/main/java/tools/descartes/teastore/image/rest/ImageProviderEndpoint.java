package tools.descartes.teastore.image.rest;

import java.util.HashMap;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.entities.ImageSize;
import tools.descartes.teastore.image.ImageProvider;
import tools.descartes.teastore.image.setup.SetupController;
@Path("image")
@Produces({"application/json"})
@Consumes({"application/json"})
public class ImageProviderEndpoint {
@POST
@Path("getProductImages")
public  Response getProductImages(HashMap<Long, String> images) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("images", images);
threadMonitoringController.enterService("_LUtKwQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LUuY4AskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854600036259947388977976 = Response.ok().entity(ImageProvider.IP.getProductImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
threadMonitoringController.exitInternalAction("_LUuY4AskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854600036259947388977976;
}
finally {
threadMonitoringController.exitService("_LUtKwQskEeyH6q2UaRYLeA");
}
}

@POST
@Path("getWebImages")
public  Response getWebUIImages(HashMap<String, String> images) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("images", images);
threadMonitoringController.enterService("_LUzRYQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LU0fgAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854610020358468789560524 = Response.ok().entity(ImageProvider.IP.getWebUIImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
threadMonitoringController.exitInternalAction("_LU0fgAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854610020358468789560524;
}
finally {
threadMonitoringController.exitService("_LUzRYQskEeyH6q2UaRYLeA");
}
}

@GET
@Path("regenerateImages")
public  Response regenerateImages() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_LU4w8AskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LU4w8QskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
SetupController.SETUP.reconfiguration();
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185461003059059786475521 = Response.ok().build();
threadMonitoringController.exitInternalAction("_LU4w8QskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185461003059059786475521;
}
finally {
threadMonitoringController.exitService("_LU4w8AskEeyH6q2UaRYLeA");
}
}

@GET
@Path("finished")
public  Response isFinished() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_LU7NMAskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LU70QAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118546200750867479473237 = Response.ok().entity(SetupController.SETUP.isFinished()).build();
threadMonitoringController.exitInternalAction("_LU70QAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118546200750867479473237;
}
finally {
threadMonitoringController.exitService("_LU7NMAskEeyH6q2UaRYLeA");
}
}

@GET
@Path("state")
@Produces({"text/plain"})
public  Response getState() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_LU9pcQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LU-3kAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854620035690854881938017 = Response.ok().entity(SetupController.SETUP.getState()).build();
threadMonitoringController.exitInternalAction("_LU-3kAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854620035690854881938017;
}
finally {
threadMonitoringController.exitService("_LU9pcQskEeyH6q2UaRYLeA");
}
}

@POST
@Path("setCacheSize")
public  Response setCacheSize(long cacheSize) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("cacheSize", cacheSize);
threadMonitoringController.enterService("_LVAswAskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LVBT0AskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118546300741347668934013 = Response.ok().entity(SetupController.SETUP.setCacheSize(cacheSize)).build();
threadMonitoringController.exitInternalAction("_LVBT0AskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118546300741347668934013;
}
finally {
threadMonitoringController.exitService("_LVAswAskEeyH6q2UaRYLeA");
}
}

}
