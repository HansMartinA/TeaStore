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
if (item == null)
{
throw new  NullPointerException("OrderItem must not be null.");
}
LinkedList<OrderItem> list = new  LinkedList<OrderItem>();
list.add(item);
List<Long> recommended = RecommenderSelector.getInstance().recommendProducts(uid, list);
return Response.ok().entity(recommended).build();
}
finally {
threadMonitoringController.exitService("_5A4YkPhgEeudoMIzjEj1xQ");
}
}

}
