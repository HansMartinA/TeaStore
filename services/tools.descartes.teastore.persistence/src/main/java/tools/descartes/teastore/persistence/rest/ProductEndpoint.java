package tools.descartes.teastore.persistence.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.persistence.domain.ProductRepository;
import tools.descartes.teastore.persistence.repository.DataGenerator;
import tools.descartes.teastore.registryclient.util.AbstractCRUDEndpoint;
import tools.descartes.teastore.entities.Product;
@Path("products")
public class ProductEndpoint extends AbstractCRUDEndpoint<Product> {
@Override
protected  long createEntity(final Product product) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return  - 1L;
}
return ProductRepository.REPOSITORY.createEntity(product);
}

@Override
protected  Product findEntityById(final long id) {
Product product = ProductRepository.REPOSITORY.getEntity(id);
if (product == null)
{
return null;
}
return new  Product(product);
}

@Override
protected  List<Product> listAllEntities(final int startIndex, final int maxResultCount) {
List<Product> products = new  ArrayList<Product>();
for (Product p : ProductRepository.REPOSITORY.getAllEntities(startIndex, maxResultCount))
{
products.add(new  Product(p));
}
return products;
}

@Override
protected  boolean updateEntity(long id, Product product) {
return ProductRepository.REPOSITORY.updateEntity(id, product);
}

@Override
protected  boolean deleteEntity(long id) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return false;
}
return ProductRepository.REPOSITORY.removeEntity(id);
}

@GET
@Path("category/{category:[0-9][0-9]*}")
public  List<Product> listAllForCategory(@PathParam("category")
final Long categoryId, @QueryParam("start")
final Integer startPosition, @QueryParam("max")
final Integer maxResult) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("categoryId", categoryId);
monitoringServiceParameters.addValue("startPosition", startPosition);
monitoringServiceParameters.addValue("maxResult", maxResult);
threadMonitoringController.enterService("_Lkn2EQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LkodIAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (categoryId == null)
{
return listAll(startPosition, maxResult);
}
List<Product> products = new  ArrayList<Product>();
for (Product p : ProductRepository.REPOSITORY.getAllEntities(categoryId, parseIntQueryParam(startPosition), parseIntQueryParam(maxResult)))
{
products.add(new  Product(p));
}
List<Product> longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185477006391088661142534 = products;
threadMonitoringController.exitInternalAction("_LkodIAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185477006391088661142534;
}
finally {
threadMonitoringController.exitService("_Lkn2EQskEeyH6q2UaRYLeA");
}
}

@GET
@Path("count/{category:[0-9][0-9]*}")
public  Response countForCategory(@PathParam("category")
final Long categoryId) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("categoryId", categoryId);
threadMonitoringController.enterService("_LktVoQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_Lkt8sAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (categoryId == null)
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854770033124401733612796 = Response.status(404).build();
threadMonitoringController.exitInternalAction("_Lkt8sAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854770033124401733612796;
}
threadMonitoringController.enterInternalAction("_LkxABAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
long count = ProductRepository.REPOSITORY.getProductCount(categoryId);
threadMonitoringController.exitInternalAction("_LkxABAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LkxnEAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (count >= 0)
{
return Response.ok(String.valueOf(count)).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185478003016744887910666 = Response.status(404).build();
threadMonitoringController.exitInternalAction("_LkxnEAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185478003016744887910666;
}
finally {
threadMonitoringController.exitService("_LktVoQskEeyH6q2UaRYLeA");
}
}

}
