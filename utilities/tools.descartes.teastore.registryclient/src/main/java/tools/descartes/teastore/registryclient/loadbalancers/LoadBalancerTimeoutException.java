package tools.descartes.teastore.registryclient.loadbalancers;

import tools.descartes.teastore.registryclient.Service;
public class LoadBalancerTimeoutException extends RuntimeException {
private static final long serialVersionUID = 5101941775644953394L;

private Service targetService;

public  LoadBalancerTimeoutException(String message, Service targetService){
super(message);
this.targetService = targetService;
}
public  Service getTargetService() {
return targetService;
}

}
