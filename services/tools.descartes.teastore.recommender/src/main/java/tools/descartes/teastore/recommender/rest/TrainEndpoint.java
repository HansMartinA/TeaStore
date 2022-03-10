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
threadMonitoringController.enterService("_qGd74AsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qGmewAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
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
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949316007123728400307338 = Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity("The (re)trainprocess failed.").build();
threadMonitoringController.exitInternalAction("_qGmewAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949316007123728400307338;
}
finally {
threadMonitoringController.exitService("_qGd74AsxEey1ZNZ-8Luqpw");
}
}

@GET
@Path("timestamp")
public  Response getTimeStamp() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_qGtMcAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qGuakAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
if (TrainingSynchronizer.getInstance().getMaxTime() == TrainingSynchronizer.DEFAULT_MAX_TIME_VALUE)
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949317009968835470852069 = Response.status(Response.Status.PRECONDITION_FAILED.getStatusCode()).entity("The collection of the current maxTime was not possible.").build();
threadMonitoringController.exitInternalAction("_qGuakAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949317009968835470852069;
}
threadMonitoringController.enterInternalAction("_qGvotAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050694931800393189341368409 = Response.ok(TrainingSynchronizer.getInstance().getMaxTime()).build();
threadMonitoringController.exitInternalAction("_qGvotAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qGwPxAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493180008409841243299221 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050694931800393189341368409;
threadMonitoringController.exitInternalAction("_qGwPxAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qGwPxQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493180024297445619119118 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493180008409841243299221;
threadMonitoringController.exitInternalAction("_qGwPxQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493180024297445619119118;
}
finally {
threadMonitoringController.exitService("_qGtMcAsxEey1ZNZ-8Luqpw");
}
}

@GET
@Path("isready")
public  Response isReady() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_qGzTEAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qGz6JAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qG0hNAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean isReady = TrainingSynchronizer.getInstance().isReady();
threadMonitoringController.exitInternalAction("_qG0hNAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qGz6JAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qG1IQAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493190041270420903330496 = Response.ok(String.valueOf(isReady)).build();
threadMonitoringController.exitInternalAction("_qG1IQAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493190041270420903330496;
}
finally {
threadMonitoringController.exitService("_qGzTEAsxEey1ZNZ-8Luqpw");
}
}

}
