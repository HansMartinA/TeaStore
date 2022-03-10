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
return Response.ok().entity(ImageProvider.IP.getProductImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
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
return Response.ok().entity(ImageProvider.IP.getWebUIImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
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
SetupController.SETUP.reconfiguration();
return Response.ok().build();
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
return Response.ok().entity(SetupController.SETUP.isFinished()).build();
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
return Response.ok().entity(SetupController.SETUP.getState()).build();
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
return Response.ok().entity(SetupController.SETUP.setCacheSize(cacheSize)).build();
}
finally {
threadMonitoringController.exitService("_8Xnf4Qd_Eeyp7eds7yI7tA");
}
}

}
