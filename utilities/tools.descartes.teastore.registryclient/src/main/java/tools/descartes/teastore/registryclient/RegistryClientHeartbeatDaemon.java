package tools.descartes.teastore.registryclient;

import com.netflix.loadbalancer.Server;
public class RegistryClientHeartbeatDaemon implements Runnable {
private Service service;

private Server server;

public  RegistryClientHeartbeatDaemon(Service service, Server server){
this.server = server;
this.service = service;
}
@Override
public  void run() {
try {
RegistryClient.getClient().registerOnce(service, server);
}
catch(Exception e){
e.printStackTrace();
throw new  RuntimeException(e);
}
}

}
