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
threadMonitoringController.enterService("_2hhaMPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_2iVSgPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Received database generation command for Persistence at " + RegistryClient.getClient().getMyServiceInstanceServer() + ".");
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666284006511959366833187 = Response.status(DataGenerator.MAINTENANCE_STATUS_CODE).build();
threadMonitoringController.exitInternalAction("_2iVSgPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666284006511959366833187;
}
threadMonitoringController.enterInternalAction("_2i228vhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(true);
threadMonitoringController.exitInternalAction("_2i228vhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2jESUPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Global maintenance mode enabled.");
threadMonitoringController.exitInternalAction("_2jESUPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2jizefhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.dropAndCreateTables();
threadMonitoringController.exitInternalAction("_2jizefhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2j0gQPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Finished dropping tables and re-initializing database schmema.");
threadMonitoringController.exitInternalAction("_2j0gQPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2kPXBPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
int categoryCount = parseQuery(categories, DataGenerator.SMALL_DB_CATEGORIES);
threadMonitoringController.exitInternalAction("_2kPXBPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2ktRE_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
int productCount = parseQuery(products, DataGenerator.SMALL_DB_PRODUCTS_PER_CATEGORY);
threadMonitoringController.exitInternalAction("_2ktRE_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2lQqs_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
int userCount = parseQuery(users, DataGenerator.SMALL_DB_USERS);
threadMonitoringController.exitInternalAction("_2lQqs_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2lfUM_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
int maxOrderCount = parseQuery(orders, DataGenerator.SMALL_DB_MAX_ORDERS_PER_USER);
threadMonitoringController.exitInternalAction("_2lfUM_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2l0rYPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
LOG.info("Initializing database creation with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.");
threadMonitoringController.enterInternalAction("_2mv4e_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2m8FsPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2nDadPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2ne4QPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2oEHE_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_2ohaEPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Executors.newSingleThreadScheduledExecutor().execute(() -> {
DataGenerator.GENERATOR.generateDatabaseContent(categoryCount, productCount, userCount, maxOrderCount);
LOG.info("Finished database generation.");
CacheManager.MANAGER.resetRemoteEMFs();
LOG.info("Finished resetting all Persistence service instances.");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(false);
LOG.info("Done. Maintenance mode disabled.");
}
);
threadMonitoringController.exitInternalAction("_2oEHE_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_2ne4QPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_2nDadPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_2m8FsPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_2mv4e_hgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.exitInternalAction("_2l0rYPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
String message = "Creating database with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.";
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086662870002701011445046686 = Response.ok(message).build();
threadMonitoringController.exitInternalAction("_2ohaEPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16470086662870002701011445046686;
}
finally {
threadMonitoringController.exitService("_2hhaMPhgEeudoMIzjEj1xQ");
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
threadMonitoringController.enterService("_3KWUwPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_3K8KpPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
boolean isFinished = DataGenerator.GENERATOR.getGenerationFinishedFlag();
threadMonitoringController.exitInternalAction("_3K8KpPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_3LTXAPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666290002285050311560145 = Response.ok(String.valueOf(isFinished)).build();
threadMonitoringController.exitInternalAction("_3LTXAPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666290002285050311560145;
}
finally {
threadMonitoringController.exitService("_3KWUwPhgEeudoMIzjEj1xQ");
}
}

@POST
@Path("maintenance")
public  Response setMaintenanceMode(final Boolean maintenanceMode) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("maintenanceMode", maintenanceMode);
threadMonitoringController.enterService("_3QvQoPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_3RuIEPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
if (maintenanceMode == null)
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666293007073888052263342 = Response.status(Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_3RuIEPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666293007073888052263342;
}
threadMonitoringController.enterInternalAction("_3SZdhPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
DataGenerator.GENERATOR.setMaintenanceModeInternal(maintenanceMode);
threadMonitoringController.exitInternalAction("_3SZdhPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_3S2wgPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666293005312968910166931 = Response.ok().build();
threadMonitoringController.exitInternalAction("_3S2wgPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666293005312968910166931;
}
finally {
threadMonitoringController.exitService("_3QvQoPhgEeudoMIzjEj1xQ");
}
}

@GET
@Path("maintenance")
public  Response isMaintenance() {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
threadMonitoringController.enterService("_3dt8sPhgEeudoMIzjEj1xQ", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_3edjlPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666296006131706363053782 = Response.ok(DataGenerator.GENERATOR.isMaintenanceMode()).build();
threadMonitoringController.exitInternalAction("_3edjlPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_3ermAPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666296006417184874593005 = longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666296006131706363053782;
threadMonitoringController.exitInternalAction("_3ermAPhgEeudoMIzjEj1xQ", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1647008666296006417184874593005;
}
finally {
threadMonitoringController.exitService("_3dt8sPhgEeudoMIzjEj1xQ");
}
}

}
