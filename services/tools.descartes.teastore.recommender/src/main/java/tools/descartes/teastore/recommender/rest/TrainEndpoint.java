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
threadMonitoringController.enterService("_8q_u4Qd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8rLVEAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789269009146194093629182 = Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity("The (re)trainprocess failed.").build();
threadMonitoringController.exitInternalAction("_8rLVEAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789269009146194093629182;
}
finally {
threadMonitoringController.exitService("_8q_u4Qd_Eeyp7eds7yI7tA");
}
}

@GET
@Path("timestamp")
public  Response getTimeStamp() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_8rT38Ad_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8rVtIAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (TrainingSynchronizer.getInstance().getMaxTime() == TrainingSynchronizer.DEFAULT_MAX_TIME_VALUE)
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789270009743718331512085 = Response.status(Response.Status.PRECONDITION_FAILED.getStatusCode()).entity("The collection of the current maxTime was not possible.").build();
threadMonitoringController.exitInternalAction("_8rVtIAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789270009743718331512085;
}
threadMonitoringController.enterInternalAction("_8rW7RAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789270008594360755004646 = Response.ok(TrainingSynchronizer.getInstance().getMaxTime()).build();
threadMonitoringController.exitInternalAction("_8rW7RAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8rXiUQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078927000710120846939106 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789270008594360755004646;
threadMonitoringController.exitInternalAction("_8rXiUQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8rXiUgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789270004287078512538487 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078927000710120846939106;
threadMonitoringController.exitInternalAction("_8rXiUgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789270004287078512538487;
}
finally {
threadMonitoringController.exitService("_8rT38Ad_Eeyp7eds7yI7tA");
}
}

@GET
@Path("isready")
public  Response isReady() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_8rca0Ad_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8rdo9Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8reQBAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean isReady = TrainingSynchronizer.getInstance().isReady();
threadMonitoringController.exitInternalAction("_8reQBAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8rdo9Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8re3EAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789271009400138905279369 = Response.ok(String.valueOf(isReady)).build();
threadMonitoringController.exitInternalAction("_8re3EAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789271009400138905279369;
}
finally {
threadMonitoringController.exitService("_8rca0Ad_Eeyp7eds7yI7tA");
}
}

}
