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
threadMonitoringController.enterInternalAction("_5ScCoPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086663150024221100106703786 = Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity("The (re)trainprocess failed.").build();
threadMonitoringController.exitInternalAction("_5ScCoPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086663150024221100106703786;
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
threadMonitoringController.enterInternalAction("_5XKxoPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
if (TrainingSynchronizer.getInstance().getMaxTime() == TrainingSynchronizer.DEFAULT_MAX_TIME_VALUE)
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086663180008668881965267494 = Response.status(Response.Status.PRECONDITION_FAILED.getStatusCode()).entity("The collection of the current maxTime was not possible.").build();
threadMonitoringController.exitInternalAction("_5XKxoPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086663180008668881965267494;
}
threadMonitoringController.enterInternalAction("_5XlBVPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666318002558782863698872 = Response.ok(TrainingSynchronizer.getInstance().getMaxTime()).build();
threadMonitoringController.exitInternalAction("_5XlBVPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_5YRk5PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086663190015566549448421751 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666318002558782863698872;
threadMonitoringController.exitInternalAction("_5YRk5PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_5YqmcPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666319002412595553193314 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086663190015566549448421751;
threadMonitoringController.exitInternalAction("_5YqmcPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666319002412595553193314;
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
threadMonitoringController.enterInternalAction("_5hzUVPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_5ikJVPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean isReady = TrainingSynchronizer.getInstance().isReady();
threadMonitoringController.exitInternalAction("_5ikJVPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_5hzUVPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_5i9x8PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666321004913180399410796 = Response.ok(String.valueOf(isReady)).build();
threadMonitoringController.exitInternalAction("_5i9x8PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666321004913180399410796;
}
finally {
threadMonitoringController.exitService("_5hCfUPhgEeudoMIzjEj1xQ");
}
}

}
