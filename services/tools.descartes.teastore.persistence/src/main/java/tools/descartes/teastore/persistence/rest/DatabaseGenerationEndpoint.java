package tools.descartes.teastore.persistence.rest;

import java.util.concurrent.Executors;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.persistence.repository.CacheManager;
import tools.descartes.teastore.persistence.repository.DataGenerator;
import tools.descartes.teastore.registryclient.RegistryClient;
@Path("generatedb")
public class DatabaseGenerationEndpoint {
private static final Logger LOG = LoggerFactory.getLogger(DatabaseGenerationEndpoint.class);

@GET
public  Response generateDataBase(@QueryParam("categories")
final Integer categories, @QueryParam("products")
final Integer products, @QueryParam("users")
final Integer users, @QueryParam("orders")
final Integer orders) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("categories", categories);
monitoringServiceParameters.addValue("products", products);
monitoringServiceParameters.addValue("users", users);
monitoringServiceParameters.addValue("orders", orders);
threadMonitoringController.enterService("_LiquQQskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LirVUAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Received database generation command for Persistence at " + RegistryClient.getClient().getMyServiceInstanceServer() + ".");
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185471006642979872393169 = Response.status(DataGenerator.MAINTENANCE_STATUS_CODE).build();
threadMonitoringController.exitInternalAction("_LirVUAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185471006642979872393169;
}
threadMonitoringController.enterInternalAction("_LitKjQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(true);
threadMonitoringController.exitInternalAction("_LitKjQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LitKjgskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Global maintenance mode enabled.");
threadMonitoringController.exitInternalAction("_LitKjgskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LiyqIgskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.dropAndCreateTables();
threadMonitoringController.exitInternalAction("_LiyqIgskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LiyqIwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Finished dropping tables and re-initializing database schmema.");
threadMonitoringController.exitInternalAction("_LiyqIwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LizRJAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
int categoryCount = parseQuery(categories, DataGenerator.SMALL_DB_CATEGORIES);
threadMonitoringController.exitInternalAction("_LizRJAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LizRKAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
int productCount = parseQuery(products, DataGenerator.SMALL_DB_PRODUCTS_PER_CATEGORY);
threadMonitoringController.exitInternalAction("_LizRKAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Liz4MwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
int userCount = parseQuery(users, DataGenerator.SMALL_DB_USERS);
threadMonitoringController.exitInternalAction("_Liz4MwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Liz4NwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
int maxOrderCount = parseQuery(orders, DataGenerator.SMALL_DB_MAX_ORDERS_PER_USER);
threadMonitoringController.exitInternalAction("_Liz4NwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Liz4OAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Initializing database creation with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.");
threadMonitoringController.enterInternalAction("_LjUOkgskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LjUOkwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LjUOmAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LjU1kAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LjU1lAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LjU1lQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Executors.newSingleThreadScheduledExecutor().execute(() -> {
DataGenerator.GENERATOR.generateDatabaseContent(categoryCount, productCount, userCount, maxOrderCount);
LOG.info("Finished database generation.");
CacheManager.MANAGER.resetRemoteEMFs();
LOG.info("Finished resetting all Persistence service instances.");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(false);
LOG.info("Done. Maintenance mode disabled.");
}
);
threadMonitoringController.exitInternalAction("_LjU1lAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_LjU1kAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_LjUOmAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_LjUOkwskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_LjUOkgskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_Liz4OAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
String message = "Creating database with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.";
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118547200049416570115155545 = Response.ok(message).build();
threadMonitoringController.exitInternalAction("_LjU1lQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163050118547200049416570115155545;
}
finally {
threadMonitoringController.exitService("_LiquQQskEeyH6q2UaRYLeA");
}
}

private  int parseQuery(Integer param, int defaultValue) {
if (param == null)
{
return defaultValue;
}
return param;
}

@GET
@Path("finished")
public  Response isFinshed() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_LjjfEAskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LjlURAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean isFinished = DataGenerator.GENERATOR.getGenerationFinishedFlag();
threadMonitoringController.exitInternalAction("_LjlURAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_Ljl7UAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185473004948415240032995 = Response.ok(String.valueOf(isFinished)).build();
threadMonitoringController.exitInternalAction("_Ljl7UAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185473004948415240032995;
}
finally {
threadMonitoringController.exitService("_LjjfEAskEeyH6q2UaRYLeA");
}
}

@POST
@Path("maintenance")
public  Response setMaintenanceMode(final Boolean maintenanceMode) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("maintenanceMode", maintenanceMode);
threadMonitoringController.enterService("_LjnwgAskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LjnwgQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (maintenanceMode == null)
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185474004696002575611328 = Response.status(Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_LjnwgQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185474004696002575611328;
}
threadMonitoringController.enterInternalAction("_LjoXlAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.setMaintenanceModeInternal(maintenanceMode);
threadMonitoringController.exitInternalAction("_LjoXlAskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LjoXlQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185474006049314871200979 = Response.ok().build();
threadMonitoringController.exitInternalAction("_LjoXlQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185474006049314871200979;
}
finally {
threadMonitoringController.exitService("_LjnwgAskEeyH6q2UaRYLeA");
}
}

@GET
@Path("maintenance")
public  Response isMaintenance() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_Ljt3IAskEeyH6q2UaRYLeA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_LjvsUQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185474001934854791419156 = Response.ok(DataGenerator.GENERATOR.isMaintenanceMode()).build();
threadMonitoringController.exitInternalAction("_LjvsUQskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_LjvsUgskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854740073326179025869 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630501185474001934854791419156;
threadMonitoringController.exitInternalAction("_LjvsUgskEeyH6q2UaRYLeA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305011854740073326179025869;
}
finally {
threadMonitoringController.exitService("_Ljt3IAskEeyH6q2UaRYLeA");
}
}

}
