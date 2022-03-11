package tools.descartes.teastore.recommender.rest;

import java.util.LinkedList;
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
@Path("recommendsingle")
@Produces({"application/json"})
@Consumes({"application/json"})
public class RecommendSingleEndpoint {
@POST
public  Response recommend(OrderItem item, @QueryParam("uid")
final Long uid) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("item", item);
monitoringServiceParameters.addValue("uid", uid);
threadMonitoringController.enterService("_5A4YkPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_5B2B4PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
if (item == null)
{
throw new  NullPointerException("OrderItem must not be null.");
}
LinkedList<OrderItem> list = new  LinkedList<OrderItem>();
list.add(item);
threadMonitoringController.exitInternalAction("_5B2B4PhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_5CXmVPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_5C2HdPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
List<Long> recommended = RecommenderSelector.getInstance().recommendProducts(uid, list);
threadMonitoringController.exitInternalAction("_5C2HdPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_5CXmVPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_5DQXIPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086663130008983758399778219 = Response.ok().entity(recommended).build();
threadMonitoringController.exitInternalAction("_5DQXIPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086663130008983758399778219;
}
finally {
threadMonitoringController.exitService("_5A4YkPhgEeudoMIzjEj1xQ");
}
}

}
