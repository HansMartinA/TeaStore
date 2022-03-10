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
threadMonitoringController.enterService("_qAZwIAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qAaXMAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Received database generation command for Persistence at " + RegistryClient.getClient().getMyServiceInstanceServer() + ".");
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949302009673798783598737 = Response.status(DataGenerator.MAINTENANCE_STATUS_CODE).build();
threadMonitoringController.exitInternalAction("_qAaXMAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949302009673798783598737;
}
threadMonitoringController.enterInternalAction("_qAdajAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(true);
threadMonitoringController.exitInternalAction("_qAdajAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qAeBkAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Global maintenance mode enabled.");
threadMonitoringController.exitInternalAction("_qAeBkAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qAoZqwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.dropAndCreateTables();
threadMonitoringController.exitInternalAction("_qAoZqwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qAoZrAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Finished dropping tables and re-initializing database schmema.");
threadMonitoringController.exitInternalAction("_qAoZrAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qApAtAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
int categoryCount = parseQuery(categories, DataGenerator.SMALL_DB_CATEGORIES);
threadMonitoringController.exitInternalAction("_qApAtAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qApAuAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
int productCount = parseQuery(products, DataGenerator.SMALL_DB_PRODUCTS_PER_CATEGORY);
threadMonitoringController.exitInternalAction("_qApAuAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qApnwQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
int userCount = parseQuery(users, DataGenerator.SMALL_DB_USERS);
threadMonitoringController.exitInternalAction("_qApnwQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qApnxQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
int maxOrderCount = parseQuery(orders, DataGenerator.SMALL_DB_MAX_ORDERS_PER_USER);
threadMonitoringController.exitInternalAction("_qApnxQsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qApnxgsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Initializing database creation with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.");
threadMonitoringController.enterInternalAction("_qBII7gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qBII7wsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qBIv8QsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qBIv8gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qBIv9gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qBIv9wsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Executors.newSingleThreadScheduledExecutor().execute(() -> {
DataGenerator.GENERATOR.generateDatabaseContent(categoryCount, productCount, userCount, maxOrderCount);
LOG.info("Finished database generation.");
CacheManager.MANAGER.resetRemoteEMFs();
LOG.info("Finished resetting all Persistence service instances.");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(false);
LOG.info("Done. Maintenance mode disabled.");
}
);
threadMonitoringController.exitInternalAction("_qBIv9gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qBIv8gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qBIv8QsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qBII7wsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qBII7gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_qApnxgsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
String message = "Creating database with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.";
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949303008833807366627809 = Response.ok(message).build();
threadMonitoringController.exitInternalAction("_qBIv9wsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949303008833807366627809;
}
finally {
threadMonitoringController.exitService("_qAZwIAsxEey1ZNZ-8Luqpw");
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
threadMonitoringController.enterService("_qBbq4AsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qBfVQwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean isFinished = DataGenerator.GENERATOR.getGenerationFinishedFlag();
threadMonitoringController.exitInternalAction("_qBfVQwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qBf8UAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949304004771427710139694 = Response.ok(String.valueOf(isFinished)).build();
threadMonitoringController.exitInternalAction("_qBf8UAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949304004771427710139694;
}
finally {
threadMonitoringController.exitService("_qBbq4AsxEey1ZNZ-8Luqpw");
}
}

@POST
@Path("maintenance")
public  Response setMaintenanceMode(final Boolean maintenanceMode) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("maintenanceMode", maintenanceMode);
threadMonitoringController.enterService("_qBi_oAsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qBkNwAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
if (maintenanceMode == null)
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493050009989945275482892 = Response.status(Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_qBkNwAsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493050009989945275482892;
}
threadMonitoringController.enterInternalAction("_qBk00QsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.setMaintenanceModeInternal(maintenanceMode);
threadMonitoringController.exitInternalAction("_qBk00QsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qBk00gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493050020808171902317885 = Response.ok().build();
threadMonitoringController.exitInternalAction("_qBk00gsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16305069493050020808171902317885;
}
finally {
threadMonitoringController.exitService("_qBi_oAsxEey1ZNZ-8Luqpw");
}
}

@GET
@Path("maintenance")
public  Response isMaintenance() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_qBqUYQsxEey1ZNZ-8Luqpw", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_qBsJkgsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949306004495340087352597 = Response.ok(DataGenerator.GENERATOR.isMaintenanceMode()).build();
threadMonitoringController.exitInternalAction("_qBsJkgsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_qBsJkwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949306004842126132598694 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949306004495340087352597;
threadMonitoringController.exitInternalAction("_qBsJkwsxEey1ZNZ-8Luqpw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630506949306004842126132598694;
}
finally {
threadMonitoringController.exitService("_qBqUYQsxEey1ZNZ-8Luqpw");
}
}

}
