package tools.descartes.teastore.registry.rest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@WebListener
public class RegistryStartup implements ServletContextListener {
private static final Logger LOG = LoggerFactory.getLogger(RegistryStartup.class);

private static final int HEARTBEAT_INTERVAL_MS = 2500;

private static ScheduledExecutorService heartbeatScheduler;

public  RegistryStartup(){
}
public  void contextDestroyed(ServletContextEvent arg0) {
heartbeatScheduler.shutdownNow();
LOG.info("Shutdown registry");
}

public  void contextInitialized(ServletContextEvent arg0) {
heartbeatScheduler = Executors.newSingleThreadScheduledExecutor();
heartbeatScheduler.scheduleAtFixedRate(new  RegistryHeartbeatDaemon(), HEARTBEAT_INTERVAL_MS, HEARTBEAT_INTERVAL_MS, TimeUnit.MILLISECONDS);
LOG.info("Registry online");
}

}
