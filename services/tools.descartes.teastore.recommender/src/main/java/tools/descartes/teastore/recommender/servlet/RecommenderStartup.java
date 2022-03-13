package tools.descartes.teastore.recommender.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.registryclient.RegistryClient;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
import tools.descartes.teastore.registryclient.util.RESTClient;
@WebListener
public class RecommenderStartup implements ServletContextListener {
private static final int REST_READ_TIMOUT = 1750;

private static final Logger LOG = LoggerFactory.getLogger(RecommenderStartup.class);

public  RecommenderStartup(){
}
public  void contextDestroyed(ServletContextEvent event) {
RegistryClient.getClient().unregister(event.getServletContext().getContextPath());
}

public  void contextInitialized(ServletContextEvent event) {
RESTClient.setGlobalReadTimeout(REST_READ_TIMOUT);
ServiceLoadBalancer.preInitializeServiceLoadBalancers(Service.PERSISTENCE);
RegistryClient.getClient().runAfterServiceIsAvailable(Service.PERSISTENCE, () -> {
TrainingSynchronizer.getInstance().retrieveDataAndRetrain();
RegistryClient.getClient().register(event.getServletContext().getContextPath());
}
, Service.RECOMMENDER);
try {
long looptime = (Long) new  InitialContext().lookup("java:comp/env/recommenderLoopTime");
if (looptime > 0)
{
new  RetrainDaemon(looptime).start();
LOG.info("Periodic retraining every " + looptime + " milliseconds");
}
else
{
LOG.info("Recommender loop time not set. Disabling periodic retraining.");
}
}
catch(NamingException e){
LOG.info("Recommender loop time not set. Disabling periodic retraining.");
}
}

}
