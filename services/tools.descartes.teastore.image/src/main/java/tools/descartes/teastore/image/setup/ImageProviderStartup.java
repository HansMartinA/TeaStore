package tools.descartes.teastore.image.setup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import tools.descartes.teastore.registryclient.RegistryClient;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.StartupCallback;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
@WebListener
public class ImageProviderStartup implements ServletContextListener {
public  ImageProviderStartup(){
}
public  void contextDestroyed(ServletContextEvent event) {
RegistryClient.getClient().unregister(event.getServletContext().getContextPath());
SetupController.SETUP.teardown();
}

public  void contextInitialized(ServletContextEvent event) {
ServiceLoadBalancer.preInitializeServiceLoadBalancers(Service.PERSISTENCE);
RegistryClient.getClient().runAfterServiceIsAvailable(Service.PERSISTENCE, new  StartupCallback(){
@Override
public  void callback() {
SetupController.SETUP.startup();
RegistryClient.getClient().register(event.getServletContext().getContextPath());
}

}
, Service.IMAGE);
}

}
