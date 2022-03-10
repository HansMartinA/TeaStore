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
threadMonitoringController.enterService("_8kh6gAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8kihkAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Received database generation command for Persistence at " + RegistryClient.getClient().getMyServiceInstanceServer() + ".");
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789254009550674889027854 = Response.status(DataGenerator.MAINTENANCE_STATUS_CODE).build();
threadMonitoringController.exitInternalAction("_8kihkAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789254009550674889027854;
}
threadMonitoringController.enterInternalAction("_8klk5wd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(true);
threadMonitoringController.exitInternalAction("_8klk5wd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kmL8Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Global maintenance mode enabled.");
threadMonitoringController.exitInternalAction("_8kmL8Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kxyMAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.dropAndCreateTables();
threadMonitoringController.exitInternalAction("_8kxyMAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kxyMQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Finished dropping tables and re-initializing database schmema.");
threadMonitoringController.exitInternalAction("_8kxyMQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kyZNAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
int categoryCount = parseQuery(categories, DataGenerator.SMALL_DB_CATEGORIES);
threadMonitoringController.exitInternalAction("_8kyZNAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kzAQwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
int productCount = parseQuery(products, DataGenerator.SMALL_DB_PRODUCTS_PER_CATEGORY);
threadMonitoringController.exitInternalAction("_8kzAQwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kznUwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
int userCount = parseQuery(users, DataGenerator.SMALL_DB_USERS);
threadMonitoringController.exitInternalAction("_8kznUwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8kznVwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
int maxOrderCount = parseQuery(orders, DataGenerator.SMALL_DB_MAX_ORDERS_PER_USER);
threadMonitoringController.exitInternalAction("_8kznVwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8k0OYAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Initializing database creation with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.");
threadMonitoringController.enterInternalAction("_8ljOPwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8lj1QAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8lj1RQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8lj1Rgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8lkcUwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8lkcVAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Executors.newSingleThreadScheduledExecutor().execute(() -> {
DataGenerator.GENERATOR.generateDatabaseContent(categoryCount, productCount, userCount, maxOrderCount);
LOG.info("Finished database generation.");
CacheManager.MANAGER.resetRemoteEMFs();
LOG.info("Finished resetting all Persistence service instances.");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(false);
LOG.info("Done. Maintenance mode disabled.");
}
);
threadMonitoringController.exitInternalAction("_8lkcUwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8lj1Rgd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8lj1RQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8lj1QAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8ljOPwd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_8k0OYAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
String message = "Creating database with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.";
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078925500691378438586602 = Response.ok(message).build();
threadMonitoringController.exitInternalAction("_8lkcVAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078925500691378438586602;
}
finally {
threadMonitoringController.exitService("_8kh6gAd_Eeyp7eds7yI7tA");
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
threadMonitoringController.enterService("_8l3-UQd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8l7BpAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean isFinished = DataGenerator.GENERATOR.getGenerationFinishedFlag();
threadMonitoringController.exitInternalAction("_8l7BpAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8l7osAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892560014475536868475547 = Response.ok(String.valueOf(isFinished)).build();
threadMonitoringController.exitInternalAction("_8l7osAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892560014475536868475547;
}
finally {
threadMonitoringController.exitService("_8l3-UQd_Eeyp7eds7yI7tA");
}
}

@POST
@Path("maintenance")
public  Response setMaintenanceMode(final Boolean maintenanceMode) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("maintenanceMode", maintenanceMode);
threadMonitoringController.enterService("_8l9d4Qd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8l-E8Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
if (maintenanceMode == null)
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892570003937376800952663 = Response.status(Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_8l-E8Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892570003937376800952663;
}
threadMonitoringController.enterInternalAction("_8l-sBAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.setMaintenanceModeInternal(maintenanceMode);
threadMonitoringController.exitInternalAction("_8l-sBAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8l-sBQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078925700007576336854141608 = Response.ok().build();
threadMonitoringController.exitInternalAction("_8l-sBQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors163010078925700007576336854141608;
}
finally {
threadMonitoringController.exitService("_8l9d4Qd_Eeyp7eds7yI7tA");
}
}

@GET
@Path("maintenance")
public  Response isMaintenance() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_8mDkgQd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_8mELlAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789257003991931705273679 = Response.ok(DataGenerator.GENERATOR.isMaintenanceMode()).build();
threadMonitoringController.exitInternalAction("_8mELlAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_8mELlQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892580024028654045014874 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630100789257003991931705273679;
threadMonitoringController.exitInternalAction("_8mELlQd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16301007892580024028654045014874;
}
finally {
threadMonitoringController.exitService("_8mDkgQd_Eeyp7eds7yI7tA");
}
}

}
