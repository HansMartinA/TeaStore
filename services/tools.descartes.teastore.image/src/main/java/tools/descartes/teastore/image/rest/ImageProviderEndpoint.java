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
threadMonitoringController.enterService("_1xiuwPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_1xtG0PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086662630012150266804203014 = Response.ok().entity(ImageProvider.IP.getProductImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
threadMonitoringController.exitInternalAction("_1xtG0PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086662630012150266804203014;
}
finally {
threadMonitoringController.exitService("_1xiuwPhgEeudoMIzjEj1xQ");
}
}

@POST
@Path("getWebImages")
public  Response getWebUIImages(HashMap<String, String> images) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("images", images);
threadMonitoringController.enterService("_1zw8UPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_10bDoPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors164700866626400004301943072362868 = Response.ok().entity(ImageProvider.IP.getWebUIImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
threadMonitoringController.exitInternalAction("_10bDoPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors164700866626400004301943072362868;
}
finally {
threadMonitoringController.exitService("_1zw8UPhgEeudoMIzjEj1xQ");
}
}

@GET
@Path("regenerateImages")
public  Response regenerateImages() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_13ovsPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_14Ie8PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
SetupController.SETUP.reconfiguration();
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666265005569582589994058 = Response.ok().build();
threadMonitoringController.exitInternalAction("_14Ie8PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666265005569582589994058;
}
finally {
threadMonitoringController.exitService("_13ovsPhgEeudoMIzjEj1xQ");
}
}

@GET
@Path("finished")
public  Response isFinished() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_16-XkPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_17dfwPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666267003261946979843129 = Response.ok().entity(SetupController.SETUP.isFinished()).build();
threadMonitoringController.exitInternalAction("_17dfwPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666267003261946979843129;
}
finally {
threadMonitoringController.exitService("_16-XkPhgEeudoMIzjEj1xQ");
}
}

@GET
@Path("state")
@Produces({"text/plain"})
public  Response getState() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_1-HLIPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_1-vdQPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086662680020538817325592462 = Response.ok().entity(SetupController.SETUP.getState()).build();
threadMonitoringController.exitInternalAction("_1-vdQPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086662680020538817325592462;
}
finally {
threadMonitoringController.exitService("_1-HLIPhgEeudoMIzjEj1xQ");
}
}

@POST
@Path("setCacheSize")
public  Response setCacheSize(long cacheSize) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("cacheSize", cacheSize);
threadMonitoringController.enterService("_2CYAEPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_2DGY0PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors164700866626900698274984812135 = Response.ok().entity(SetupController.SETUP.setCacheSize(cacheSize)).build();
threadMonitoringController.exitInternalAction("_2DGY0PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors164700866626900698274984812135;
}
finally {
threadMonitoringController.exitService("_2CYAEPhgEeudoMIzjEj1xQ");
}
}

}
