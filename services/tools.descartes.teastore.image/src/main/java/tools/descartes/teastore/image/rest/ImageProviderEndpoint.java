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
threadMonitoringController.enterService("_8V_vQAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8WA9YAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789244005523667073118153 = Response.ok().entity(ImageProvider.IP.getProductImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
threadMonitoringController.exitInternalAction("_8WA9YAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789244005523667073118153;
}
finally {
threadMonitoringController.exitService("_8V_vQAd_Eeyp7eds7yI7tA");
}
}

@POST
@Path("getWebImages")
public  Response getWebUIImages(HashMap<String, String> images) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("images", images);
threadMonitoringController.enterService("_8WF14Ad_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8WGc8Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789245007172436089967394 = Response.ok().entity(ImageProvider.IP.getWebUIImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
threadMonitoringController.exitInternalAction("_8WGc8Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789245007172436089967394;
}
finally {
threadMonitoringController.exitService("_8WF14Ad_Eeyp7eds7yI7tA");
}
}

@GET
@Path("regenerateImages")
public  Response regenerateImages() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_8XgyMAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8XgyMQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
SetupController.SETUP.reconfiguration();
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892460013916058359425187 = Response.ok().build();
threadMonitoringController.exitInternalAction("_8XgyMQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892460013916058359425187;
}
finally {
threadMonitoringController.exitService("_8XgyMAd_Eeyp7eds7yI7tA");
}
}

@GET
@Path("finished")
public  Response isFinished() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_8XinYQd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8XjOcAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892470036591711821563155 = Response.ok().entity(SetupController.SETUP.isFinished()).build();
threadMonitoringController.exitInternalAction("_8XjOcAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892470036591711821563155;
}
finally {
threadMonitoringController.exitService("_8XinYQd_Eeyp7eds7yI7tA");
}
}

@GET
@Path("state")
@Produces({"text/plain"})
public  Response getState() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_8XlqsAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8XmRwAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078924800045973038536133104 = Response.ok().entity(SetupController.SETUP.getState()).build();
threadMonitoringController.exitInternalAction("_8XmRwAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078924800045973038536133104;
}
finally {
threadMonitoringController.exitService("_8XlqsAd_Eeyp7eds7yI7tA");
}
}

@POST
@Path("setCacheSize")
public  Response setCacheSize(long cacheSize) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("cacheSize", cacheSize);
threadMonitoringController.enterService("_8Xnf4Qd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8XouAAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892480043384528126015875 = Response.ok().entity(SetupController.SETUP.setCacheSize(cacheSize)).build();
threadMonitoringController.exitInternalAction("_8XouAAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892480043384528126015875;
}
finally {
threadMonitoringController.exitService("_8Xnf4Qd_Eeyp7eds7yI7tA");
}
}

}
