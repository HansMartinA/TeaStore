package tools.descartes.teastore.persistence.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import tools.descartes.teastore.persistence.domain.OrderItemRepository;
import tools.descartes.teastore.persistence.repository.DataGenerator;
import tools.descartes.teastore.registryclient.util.AbstractCRUDEndpoint;
import tools.descartes.teastore.entities.OrderItem;
@Path("orderitems")
public class OrderItemEndpoint extends AbstractCRUDEndpoint<OrderItem> {
@Override
protected  long createEntity(final OrderItem orderItem) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return  - 1L;
}
return OrderItemRepository.REPOSITORY.createEntity(orderItem);
}

@Override
protected  OrderItem findEntityById(final long id) {
OrderItem item = OrderItemRepository.REPOSITORY.getEntity(id);
if (item == null)
{
return null;
}
return new  OrderItem(item);
}

@Override
protected  List<OrderItem> listAllEntities(final int startIndex, final int maxResultCount) {
List<OrderItem> orderItems = new  ArrayList<OrderItem>();
for (OrderItem oi : OrderItemRepository.REPOSITORY.getAllEntities(startIndex, maxResultCount))
{
orderItems.add(new  OrderItem(oi));
}
return orderItems;
}

@Override
protected  boolean updateEntity(long id, OrderItem orderItem) {
return OrderItemRepository.REPOSITORY.updateEntity(id, orderItem);
}

@Override
protected  boolean deleteEntity(long id) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return false;
}
return OrderItemRepository.REPOSITORY.removeEntity(id);
}

@GET
@Path("product/{product:[0-9][0-9]*}")
public  List<OrderItem> listAllForProduct(@PathParam("product")
final Long productId, @QueryParam("start")
final Integer startPosition, @QueryParam("max")
final Integer maxResult) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("productId", productId);
monitoringServiceParameters.addValue("startPosition", startPosition);
monitoringServiceParameters.addValue("maxResult", maxResult);
threadMonitoringController.enterService("_3xDIYPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
if (productId == null)
{
return listAll(startPosition, maxResult);
}
List<OrderItem> orderItems = new  ArrayList<OrderItem>();
for (OrderItem oi : OrderItemRepository.REPOSITORY.getAllEntitiesWithProduct(productId, parseIntQueryParam(startPosition), parseIntQueryParam(maxResult)))
{
orderItems.add(new  OrderItem(oi));
}
return orderItems;
}
finally {
threadMonitoringController.exitService("_3xDIYPhgEeudoMIzjEj1xQ");
}
}

@GET
@Path("order/{order:[0-9][0-9]*}")
public  List<OrderItem> listAllForOrder(@PathParam("order")
final Long orderId, @QueryParam("start")
final Integer startPosition, @QueryParam("max")
final Integer maxResult) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("orderId", orderId);
monitoringServiceParameters.addValue("startPosition", startPosition);
monitoringServiceParameters.addValue("maxResult", maxResult);
threadMonitoringController.enterService("_35AKgPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
if (orderId == null)
{
return listAll(startPosition, maxResult);
}
List<OrderItem> orderItems = new  ArrayList<OrderItem>();
for (OrderItem oi : OrderItemRepository.REPOSITORY.getAllEntitiesWithOrder(orderId, parseIntQueryParam(startPosition), parseIntQueryParam(maxResult)))
{
orderItems.add(new  OrderItem(oi));
}
return orderItems;
}
finally {
threadMonitoringController.exitService("_35AKgPhgEeudoMIzjEj1xQ");
}
}

}
