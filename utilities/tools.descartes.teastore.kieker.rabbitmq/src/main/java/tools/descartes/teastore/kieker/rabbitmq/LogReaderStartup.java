package tools.descartes.teastore.kieker.rabbitmq;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.BasicConfigurator;
@WebListener
public class LogReaderStartup implements ServletContextListener {
private static ScheduledExecutorService logReaderStarter;

private static ScheduledExecutorService fileWriterStarter;

public  LogReaderStartup(){
}
public  void contextDestroyed(ServletContextEvent event) {
stopFileWriter();
logReaderStarter.shutdownNow();
try {
logReaderStarter.awaitTermination(10, TimeUnit.SECONDS);
}
catch(InterruptedException e){
e.printStackTrace();
}
}

public static  void stopFileWriter() {
fileWriterStarter.shutdownNow();
try {
fileWriterStarter.awaitTermination(10, TimeUnit.SECONDS);
}
catch(InterruptedException e){
e.printStackTrace();
}
}

public  void contextInitialized(ServletContextEvent event) {
startFileWriter();
logReaderStarter = Executors.newSingleThreadScheduledExecutor();
BasicConfigurator.configure();
logReaderStarter.schedule(new  LogReaderDaemon(), 10, TimeUnit.SECONDS);
}

public static  void startFileWriter() {
fileWriterStarter = Executors.newSingleThreadScheduledExecutor();
fileWriterStarter.schedule(new  FileWriterDaemon(), 10, TimeUnit.SECONDS);
}

}
