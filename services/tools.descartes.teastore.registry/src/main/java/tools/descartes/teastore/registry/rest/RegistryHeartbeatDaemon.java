package tools.descartes.teastore.registry.rest;

import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RegistryHeartbeatDaemon implements Runnable {
private static final Logger LOG = LoggerFactory.getLogger(RegistryHeartbeatDaemon.class);

@Override
public  void run() {
try {
Registry.getRegistryInstance().getMap().entrySet().stream().forEach(entry -> {
for (Iterator<String> iter = entry.getValue().iterator() ; iter.hasNext() ; )
{
String location = iter.next();
if (!Registry.getRegistryInstance().isAlive(entry.getKey(), location))
{
iter.remove();
LOG.warn("Removed " + entry.getKey() + "@" + location + " since it failed the heartbeat!");
}
}
}
);
}
catch(Exception e){
e.printStackTrace();
throw new  RuntimeException(e);
}
}

}
