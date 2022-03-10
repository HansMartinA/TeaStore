package tools.descartes.teastore.registry.rest;

public class HeartbeatInfo {
private long lastHeartbeat;

public  HeartbeatInfo(){
this.lastHeartbeat = System.currentTimeMillis();
}
public  void newHeartbeat() {
this.lastHeartbeat = System.currentTimeMillis();
}

public  boolean isAlive() {
return System.currentTimeMillis() - lastHeartbeat < 10000;
}

}
