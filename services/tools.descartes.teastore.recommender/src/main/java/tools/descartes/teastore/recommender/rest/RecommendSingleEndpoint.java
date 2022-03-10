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
threadMonitoringController.enterService("_qGOEQAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qGOEQQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
if (item == null)
{
throw new  NullPointerException("OrderItem must not be null.");
}
LinkedList<OrderItem> list = new  LinkedList<OrderItem>();
list.add(item);
threadMonitoringController.exitInternalAction("_qGOEQQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qGRupAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qGSVtAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
List<Long> recommended = RecommenderSelector.getInstance().recommendProducts(uid, list);
threadMonitoringController.exitInternalAction("_qGSVtAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qGRupAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qGSVtQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493150048733657121351603 = Response.ok().entity(recommended).build();
threadMonitoringController.exitInternalAction("_qGSVtQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493150048733657121351603;
}
finally {
threadMonitoringController.exitService("_qGOEQAsxEey1ZNZ-8Luqpw");
}
}

}
