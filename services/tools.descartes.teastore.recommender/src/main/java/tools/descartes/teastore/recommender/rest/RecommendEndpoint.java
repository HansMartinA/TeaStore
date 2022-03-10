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
threadMonitoringController.enterService("_LoLgYAskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LoN8ogskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LoN8pwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
List<Long> recommended = RecommenderSelector.getInstance().recommendProducts(uid, currentItems);
threadMonitoringController.exitInternalAction("_LoN8pwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_LoN8ogskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LoOjsAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118548000220976083786099 = Response.ok().entity(recommended).build();
threadMonitoringController.exitInternalAction("_LoOjsAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118548000220976083786099;
}
finally {
threadMonitoringController.exitService("_LoLgYAskEeyH6q2UaRYLeA");
}
}

}
