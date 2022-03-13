package tools.descartes.teastore.recommender.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.recommender.algorithm.IRecommender;
import tools.descartes.teastore.recommender.servlet.TrainingSynchronizer;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.OrderItem;
@Path("train")
@Produces({"text/plain", "application/json"})
public class TrainEndpoint {
@GET
public  Response train() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_5RckIPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
try {
long start = System.currentTimeMillis();
long number = TrainingSynchronizer.getInstance().retrieveDataAndRetrain();
long time = System.currentTimeMillis() - start;
if (number !=  - 1)
{
return Response.ok("The (re)train was succesfully done. It took " + time + "ms and " + number + " of Orderitems were retrieved from the database.").build();
}
}
catch(Exception e){
e.printStackTrace();
}
return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity("The (re)trainprocess failed.").build();
}
finally {
threadMonitoringController.exitService("_5RckIPhgEeudoMIzjEj1xQ");
}
}

@GET
@Path("timestamp")
public  Response getTimeStamp() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_5WKFAPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
if (TrainingSynchronizer.getInstance().getMaxTime() == TrainingSynchronizer.DEFAULT_MAX_TIME_VALUE)
{
return Response.status(Response.Status.PRECONDITION_FAILED.getStatusCode()).entity("The collection of the current maxTime was not possible.").build();
}
return Response.ok(TrainingSynchronizer.getInstance().getMaxTime()).build();
}
finally {
threadMonitoringController.exitService("_5WKFAPhgEeudoMIzjEj1xQ");
}
}

@GET
@Path("isready")
public  Response isReady() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_5hCfUPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
boolean isReady = TrainingSynchronizer.getInstance().isReady();
return Response.ok(String.valueOf(isReady)).build();
}
finally {
threadMonitoringController.exitService("_5hCfUPhgEeudoMIzjEj1xQ");
}
}

}
