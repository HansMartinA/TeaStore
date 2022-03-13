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
LOG.info("Received database generation command for Persistence at " + RegistryClient.getClient().getMyServiceInstanceServer() + ".");
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return Response.status(DataGenerator.MAINTENANCE_STATUS_CODE).build();
}
DataGenerator.GENERATOR.setMaintenanceModeGlobal(true);
LOG.info("Global maintenance mode enabled.");
DataGenerator.GENERATOR.dropAndCreateTables();
LOG.info("Finished dropping tables and re-initializing database schmema.");
int categoryCount = parseQuery(categories, DataGenerator.SMALL_DB_CATEGORIES);
int productCount = parseQuery(products, DataGenerator.SMALL_DB_PRODUCTS_PER_CATEGORY);
int userCount = parseQuery(users, DataGenerator.SMALL_DB_USERS);
int maxOrderCount = parseQuery(orders, DataGenerator.SMALL_DB_MAX_ORDERS_PER_USER);
LOG.info("Initializing database creation with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.");
Executors.newSingleThreadScheduledExecutor().execute(() -> {
DataGenerator.GENERATOR.generateDatabaseContent(categoryCount, productCount, userCount, maxOrderCount);
LOG.info("Finished database generation.");
CacheManager.MANAGER.resetRemoteEMFs();
LOG.info("Finished resetting all Persistence service instances.");
DataGenerator.GENERATOR.setMaintenanceModeGlobal(false);
LOG.info("Done. Maintenance mode disabled.");
}
);
String message = "Creating database with " + categoryCount + " categories, " + productCount + " products per category, " + userCount + " users, " + maxOrderCount + " max orders per user.";
return Response.ok(message).build();
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
boolean isFinished = DataGenerator.GENERATOR.getGenerationFinishedFlag();
return Response.ok(String.valueOf(isFinished)).build();
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
if (maintenanceMode == null)
{
return Response.status(Status.NOT_FOUND).build();
}
DataGenerator.GENERATOR.setMaintenanceModeInternal(maintenanceMode);
return Response.ok().build();
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
return Response.ok(DataGenerator.GENERATOR.isMaintenanceMode()).build();
}
finally {
threadMonitoringController.exitService("_3dt8sPhgEeudoMIzjEj1xQ");
}
}

}
