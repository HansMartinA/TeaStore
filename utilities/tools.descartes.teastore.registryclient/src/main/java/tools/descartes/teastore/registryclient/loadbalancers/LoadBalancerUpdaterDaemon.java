package tools.descartes.teastore.registryclient.loadbalancers;

public class LoadBalancerUpdaterDaemon implements Runnable {
@Override
public  void run() {
try {
ServiceLoadBalancer.updateLoadBalancersForKnownServicesUsingRegistry();
}
catch(Exception e){
e.printStackTrace();
throw new  RuntimeException(e);
}
}

}
