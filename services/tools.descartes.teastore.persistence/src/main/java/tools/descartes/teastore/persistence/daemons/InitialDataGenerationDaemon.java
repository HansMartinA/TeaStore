package tools.descartes.teastore.persistence.daemons;

import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.persistence.repository.DataGenerator;
import tools.descartes.teastore.registryclient.RegistryClient;
@WebListener
public class InitialDataGenerationDaemon implements ServletContextListener {
private static final Logger LOG = LoggerFactory.getLogger(InitialDataGenerationDaemon.class);

private static final long DATABASE_OFFLINE_WAIT_MS = 2000;

public  InitialDataGenerationDaemon(){
}
public  void contextDestroyed(ServletContextEvent event) {
RegistryClient.getClient().unregister(event.getServletContext().getContextPath());
}

public  void contextInitialized(ServletContextEvent event) {
waitForDatabase();
if (DataGenerator.GENERATOR.isDatabaseEmpty())
{
LOG.info("Database is empty. Generating new database content");
DataGenerator.GENERATOR.generateDatabaseContent(DataGenerator.SMALL_DB_CATEGORIES, DataGenerator.SMALL_DB_PRODUCTS_PER_CATEGORY, DataGenerator.SMALL_DB_USERS, DataGenerator.SMALL_DB_MAX_ORDERS_PER_USER);
}
else
{
LOG.info("Populated database found. Skipping data generation");
}
LOG.info("Persistence finished initializing database");
RegistryClient.getClient().register(event.getServletContext().getContextPath());
LOG.info("Persistence started registration daemon");
}

private  void waitForDatabase() {
boolean databaseOffline = true;
while (databaseOffline)
{
try {
DataGenerator.GENERATOR.isDatabaseEmpty();
databaseOffline = false;
}
catch(PersistenceException e){
LOG.warn("Exception connecting to database. Is database offline? Wating for " + DATABASE_OFFLINE_WAIT_MS + " ms.");
try {
Thread.sleep(DATABASE_OFFLINE_WAIT_MS);
}
catch(InterruptedException e1){
LOG.error("Exception waiting for database to come online: " + e1.getMessage());
}
}
}
}

}
