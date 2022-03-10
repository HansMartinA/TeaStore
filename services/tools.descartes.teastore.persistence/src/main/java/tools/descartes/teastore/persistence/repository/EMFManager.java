package tools.descartes.teastore.persistence.repository;

import java.util.HashMap;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
final class EMFManager {
private static EntityManagerFactory emf = null;

private static HashMap<String, String> persistenceProperties = null;

private static final Logger LOG = LoggerFactory.getLogger(EMFManager.class);

private static final String MYSQL_URL_PREFIX = "jdbc:mysql://";

private static final String MYSQL_URL_POSTFIX = "/teadb";

private static final String MYSQL_DEFAULT_HOST = "localhost";

private static final String MYSQL_DEFAULT_PORT = "3306";

private  EMFManager(){
}
static  void configureEMFWithProperties(HashMap<String, String> persistenceProperties) {
EMFManager.persistenceProperties = persistenceProperties;
clearEMF();
}

static synchronized  EntityManagerFactory getEMF() {
if (emf == null)
{
HashMap<String, String> persistenceProperties = EMFManager.persistenceProperties;
if (persistenceProperties == null)
{
persistenceProperties = createPersistencePropertiesFromJavaEnv();
}
emf = Persistence.createEntityManagerFactory("tools.descartes.teastore.persistence", persistenceProperties);
}
return emf;
}

static  void clearEMF() {
if (emf != null)
{
emf.close();
}
emf = null;
}

private static  HashMap<String, String> createPersistencePropertiesFromJavaEnv() {
HashMap<String, String> persistenceProperties = new  HashMap<String, String>();
String dbhost = null;
String dbport = null;
String url = MYSQL_URL_PREFIX;
try {
dbhost = (String) new  InitialContext().lookup("java:comp/env/databaseHost");
}
catch(NamingException e){
LOG.info("Database host not set. Falling back to default host at " + MYSQL_DEFAULT_HOST + ".");
}
try {
dbport = (String) new  InitialContext().lookup("java:comp/env/databasePort");
}
catch(NamingException e){
LOG.info("Database port not set. Falling back to default host at " + MYSQL_DEFAULT_PORT + ".");
}
if (dbhost != null || dbport != null)
{
if (dbhost != null)
{
url += dbhost;
}
else
{
url += MYSQL_DEFAULT_HOST;
}
url += ":";
if (dbport != null)
{
url += dbport;
}
else
{
url += MYSQL_DEFAULT_PORT;
}
url += MYSQL_URL_POSTFIX;
LOG.info("Setting jdbc url to \"" + url + "\".");
persistenceProperties.put("javax.persistence.jdbc.url", url);
}
return persistenceProperties;
}

}
