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
threadMonitoringController.enterInternalAction("_86CQcAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
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
threadMonitoringController.exitInternalAction("_86CQcAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
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
threadMonitoringController.enterInternalAction("_86j04Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
File[]  files = folder.listFiles();
if (files != null)
{
threadMonitoringController.enterBranch("_86qikAd_Eeyp7eds7yI7tA");
java.util.concurrent.atomic.AtomicInteger loopIterationCounter1 = new  java.util.concurrent.atomic.AtomicInteger(0);
for (File f : files)
{
loopIterationCounter1.getAndIncrement();
if (f.isDirectory())
{
threadMonitoringController.enterBranch("_86u0AAd_Eeyp7eds7yI7tA");
threadMonitoringController.setExternalCallId("_86wCIAd_Eeyp7eds7yI7tA");
deleteFolder(f);
}
else
{
threadMonitoringController.enterInternalAction("_86xQRAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
f.delete();
threadMonitoringController.exitInternalAction("_86xQRAd_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
}
}
threadMonitoringController.exitLoop("_86uM8Ad_Eeyp7eds7yI7tA", loopIterationCounter1.get());
}
folder.delete();
threadMonitoringController.exitInternalAction("_86j04Ad_Eeyp7eds7yI7tA", "_oro4gG3fEdy4YaaT-RYrLQ");
}
finally {
threadMonitoringController.exitService("_86imwAd_Eeyp7eds7yI7tA");
}
}

}
