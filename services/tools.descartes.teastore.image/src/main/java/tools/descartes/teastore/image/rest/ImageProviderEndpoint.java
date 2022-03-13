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
return Response.ok().entity(ImageProvider.IP.getProductImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
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
return Response.ok().entity(ImageProvider.IP.getWebUIImages(images.entrySet().parallelStream().collect(Collectors.toMap(e -> e.getKey(), e -> ImageSize.parseImageSize(e.getValue()))))).build();
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
SetupController.SETUP.reconfiguration();
return Response.ok().build();
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
return Response.ok().entity(SetupController.SETUP.isFinished()).build();
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
return Response.ok().entity(SetupController.SETUP.getState()).build();
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
return Response.ok().entity(SetupController.SETUP.setCacheSize(cacheSize)).build();
}
finally {
threadMonitoringController.exitService("_2CYAEPhgEeudoMIzjEj1xQ");
}
}

}
