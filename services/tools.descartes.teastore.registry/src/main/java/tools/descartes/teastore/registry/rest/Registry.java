package tools.descartes.teastore.registry.rest;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public final class Registry {
private static Registry registry = new  Registry();

private Map<String, List<String>> map = new  ConcurrentHashMap<String, List<String>>();

private Map<String, HeartbeatInfo> heartbeatMap = new  ConcurrentHashMap<String, HeartbeatInfo>();

private static final Logger LOG = LoggerFactory.getLogger(Registry.class);

private  Registry(){
}
public  Map<String, HeartbeatInfo> getHeartbeatMap() {
return heartbeatMap;
}

public  Map<String, List<String>> getMap() {
return map;
}

public static  Registry getRegistryInstance() {
return registry;
}

public synchronized  List<String> getLocations(String name) {
return getFromMap(name);
}

private  List<String> getFromMap(String name) {
if (map.get(name) == null)
{
map.put(name, new  LinkedList<String>());
}
return map.get(name);
}

private  void updateHeartbeatMap(String name, String location) {
HeartbeatInfo info = getFromHeartbeatMap(name, location);
if (info == null)
{
heartbeatMap.put(name + location, new  HeartbeatInfo());
}
else
{
info.newHeartbeat();
}
}

private  HeartbeatInfo getFromHeartbeatMap(String name, String location) {
return heartbeatMap.get(name + location);
}

public  boolean isAlive(String name, String location) {
return getFromHeartbeatMap(name, location).isAlive();
}

public synchronized  boolean unregister(String name, String location) {
if (map.get(name) == null)
{
return false;
}
List<String> locations = map.get(name);
boolean removed = locations.remove(location);
if (locations.size() == 0)
{
map.remove(name);
}
if (removed)
{
LOG.info("Unregistered " + name + "@" + location);
}
return removed;
}

public synchronized  boolean register(String name, String location) {
List<String> locations = getFromMap(name);
updateHeartbeatMap(name, location);
if (locations.contains(location))
{
return false;
}
LOG.info("Registered " + name + "@" + location);
locations.add(location);
return true;
}

}
