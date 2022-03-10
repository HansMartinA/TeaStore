package tools.descartes.teastore.persistence.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import tools.descartes.teastore.persistence.domain.OrderRepository;
import tools.descartes.teastore.persistence.repository.DataGenerator;
import tools.descartes.teastore.registryclient.util.AbstractCRUDEndpoint;
import tools.descartes.teastore.entities.Order;
@Path("orders")
public class OrderEndpoint extends AbstractCRUDEndpoint<Order> {
@Override
protected  long createEntity(final Order order) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return  - 1L;
}
return OrderRepository.REPOSITORY.createEntity(order);
}

@Override
protected  Order findEntityById(final long id) {
Order order = OrderRepository.REPOSITORY.getEntity(id);
if (order == null)
{
return null;
}
return new  Order(order);
}

@Override
protected  List<Order> listAllEntities(final int startIndex, final int maxResultCount) {
List<Order> order = new  ArrayList<Order>();
for (Order o : OrderRepository.REPOSITORY.getAllEntities(startIndex, maxResultCount))
{
order.add(new  Order(o));
}
return order;
}

@Override
protected  boolean updateEntity(long id, Order order) {
return OrderRepository.REPOSITORY.updateEntity(id, order);
}

@Override
protected  boolean deleteEntity(long id) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return false;
}
return OrderRepository.REPOSITORY.removeEntity(id);
}

@GET
@Path("user/{user:[0-9][0-9]*}")
public  List<Order> listAllForUser(@PathParam("user")
final Long userId, @QueryParam("start")
final Integer startPosition, @QueryParam("max")
final Integer maxResult) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("userId", userId);
monitoringServiceParameters.addValue("startPosition", startPosition);
monitoringServiceParameters.addValue("maxResult", maxResult);
threadMonitoringController.enterService("_qCk6YQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qCmIgAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
if (userId == null)
{
return listAll(startPosition, maxResult);
}
List<Order> orders = new  ArrayList<Order>();
for (Order o : OrderRepository.REPOSITORY.getAllEntitiesWithUser(userId, parseIntQueryParam(startPosition), parseIntQueryParam(maxResult)))
{
orders.add(new  Order(o));
}
List<Order> longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050694930700026288825697390106 = orders;
threadMonitoringController.exitInternalAction("_qCmIgAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050694930700026288825697390106;
}
finally {
threadMonitoringController.exitService("_qCk6YQsxEey1ZNZ-8Luqpw");
}
}

}
