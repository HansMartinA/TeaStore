package tools.descartes.teastore.recommender.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.recommender.algorithm.RecommenderSelector;
import tools.descartes.teastore.entities.OrderItem;
import tools.descartes.teastore.entities.Product;
import tools.descartes.teastore.entities.User;
@Path("recommend")
@Produces({"application/json"})
@Consumes({"application/json"})
public class RecommendEndpoint {
@POST
public  Response recommend(List<OrderItem> currentItems, @QueryParam("uid")
final Long uid) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("currentItems", currentItems);
monitoringServiceParameters.addValue("uid", uid);
threadMonitoringController.enterService("_8qeKcAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8qib5Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8qjC9Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
List<Long> recommended = RecommenderSelector.getInstance().recommendProducts(uid, currentItems);
threadMonitoringController.exitInternalAction("_8qjC9Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8qib5Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8qjqAAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789267005569794847715658 = Response.ok().entity(recommended).build();
threadMonitoringController.exitInternalAction("_8qjqAAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789267005569794847715658;
}
finally {
threadMonitoringController.exitService("_8qeKcAd_Eeyp7eds7yI7tA");
}
}

}
