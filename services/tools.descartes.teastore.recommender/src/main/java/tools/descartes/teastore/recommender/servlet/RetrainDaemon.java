package tools.descartes.teastore.recommender.servlet;

import tools.descartes.teastore.registryclient.RegistryClient;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.StartupCallback;
public class RetrainDaemon extends Thread {
private long looptime;

public  RetrainDaemon(long looptime){
super();
setDaemon(true);
this.looptime = looptime;
}
@Override
public  void run() {
super.run();
while (true)
{
try {
Thread.sleep(looptime);
}
catch(InterruptedException e){
e.printStackTrace();
}
RegistryClient.getClient().runAfterServiceIsAvailable(Service.PERSISTENCE, new  StartupCallback(){
@Override
public  void callback() {
TrainingSynchronizer.getInstance().retrieveDataAndRetrain();
}

}
, Service.RECOMMENDER);
}
}

}
