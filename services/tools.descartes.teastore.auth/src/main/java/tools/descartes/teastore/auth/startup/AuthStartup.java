package tools.descartes.teastore.auth.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import tools.descartes.teastore.registryclient.RegistryClient;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
import tools.descartes.teastore.registryclient.util.RESTClient;
@WebListener
public class AuthStartup implements ServletContextListener {
private static final int REST_READ_TIMOUT = 1750;

public  AuthStartup(){
}
public  void contextDestroyed(ServletContextEvent event) {
RegistryClient.getClient().unregister(event.getServletContext().getContextPath());
}

public  void contextInitialized(ServletContextEvent event) {
RESTClient.setGlobalReadTimeout(REST_READ_TIMOUT);
ServiceLoadBalancer.preInitializeServiceLoadBalancers(Service.PERSISTENCE, Service.RECOMMENDER);
RegistryClient.getClient().register(event.getServletContext().getContextPath());
}

}
