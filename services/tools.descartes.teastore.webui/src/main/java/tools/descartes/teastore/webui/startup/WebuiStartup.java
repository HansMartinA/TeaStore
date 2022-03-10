package tools.descartes.teastore.webui.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import tools.descartes.teastore.registryclient.RegistryClient;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
@WebListener
public class WebuiStartup implements ServletContextListener {
public  WebuiStartup(){
}
public  void contextDestroyed(ServletContextEvent event) {
RegistryClient.getClient().unregister(event.getServletContext().getContextPath());
}

public  void contextInitialized(ServletContextEvent event) {
ServiceLoadBalancer.preInitializeServiceLoadBalancers(Service.AUTH, Service.IMAGE, Service.PERSISTENCE, Service.RECOMMENDER);
RegistryClient.getClient().register(event.getServletContext().getContextPath());
}

}
