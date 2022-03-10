package tools.descartes.teastore.kieker.rabbitmq;

import java.io.File;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/reset")
public class Reset extends HttpServlet {
private static final long serialVersionUID = 1L;

@Override
protected  void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
LogReaderStartup.stopFileWriter();
MemoryLogStorage.clearMemoryStorage();
deleteFolder(new  File("apache-tomcat-8.5.24/webapps/logs"), "kieker");
LogReaderStartup.startFileWriter();
}

public  void deleteFolder(File folder, String prefix) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("folder", folder);
monitoringServiceParameters.addValue("prefix", prefix);
threadMonitoringController.enterService("_86BpYAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
File[]  files = folder.listFiles();
if (files != null)
{
for (File f : files)
{
if (f.getPath().contains(prefix))
{
if (f.isDirectory())
{
deleteFolder(f);
}
else
{
f.delete();
}
}
}
}
}
finally {
threadMonitoringController.exitService("_86BpYAd_Eeyp7eds7yI7tA");
}
}

public  void deleteFolder(File folder) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("folder", folder);
threadMonitoringController.enterService("_86imwAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
File[]  files = folder.listFiles();
if (files != null)
{
for (File f : files)
{
if (f.isDirectory())
{
deleteFolder(f);
}
else
{
f.delete();
}
}
}
folder.delete();
}
finally {
threadMonitoringController.exitService("_86imwAd_Eeyp7eds7yI7tA");
}
}

}
