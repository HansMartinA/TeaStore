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
threadMonitoringController.enterService("_p0WRIAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_p0XfQAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949295003242275392399584 = Response.ok().entity(ImageProvider.IP.getProductImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
threadMonitoringController.exitInternalAction("_p0XfQAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949295003242275392399584;
}
finally {
threadMonitoringController.exitService("_p0WRIAsxEey1ZNZ-8Luqpw");
}
}

@POST
@Path("getWebImages")
public  Response getWebUIImages(HashMap<String, String> images) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("images", images);
threadMonitoringController.enterService("_p0bwsQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_p0dl4AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949296002167479889579894 = Response.ok().entity(ImageProvider.IP.getWebUIImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
threadMonitoringController.exitInternalAction("_p0dl4AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949296002167479889579894;
}
finally {
threadMonitoringController.exitService("_p0bwsQsxEey1ZNZ-8Luqpw");
}
}

@GET
@Path("regenerateImages")
public  Response regenerateImages() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_p0hQQQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_p0h3UAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
SetupController.SETUP.reconfiguration();
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949296008134482977746055 = Response.ok().build();
threadMonitoringController.exitInternalAction("_p0h3UAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949296008134482977746055;
}
finally {
threadMonitoringController.exitService("_p0hQQQsxEey1ZNZ-8Luqpw");
}
}

@GET
@Path("finished")
public  Response isFinished() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_p0jFcQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_p0jsgAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949297009297902849313701 = Response.ok().entity(SetupController.SETUP.isFinished()).build();
threadMonitoringController.exitInternalAction("_p0jsgAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949297009297902849313701;
}
finally {
threadMonitoringController.exitService("_p0jFcQsxEey1ZNZ-8Luqpw");
}
}

@GET
@Path("state")
@Produces({"text/plain"})
public  Response getState() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_p0k6oQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_p0lhsAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492970040740222769695844 = Response.ok().entity(SetupController.SETUP.getState()).build();
threadMonitoringController.exitInternalAction("_p0lhsAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492970040740222769695844;
}
finally {
threadMonitoringController.exitService("_p0k6oQsxEey1ZNZ-8Luqpw");
}
}

@POST
@Path("setCacheSize")
public  Response setCacheSize(long cacheSize) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("cacheSize", cacheSize);
threadMonitoringController.enterService("_p0nW4AsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_p0n98AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492980049697210712219586 = Response.ok().entity(SetupController.SETUP.setCacheSize(cacheSize)).build();
threadMonitoringController.exitInternalAction("_p0n98AsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069492980049697210712219586;
}
finally {
threadMonitoringController.exitService("_p0nW4AsxEey1ZNZ-8Luqpw");
}
}

}
