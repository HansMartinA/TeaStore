package tools.descartes.teastore.kieker.rabbitmq;

import kieker.analysis.IProjectContext;
import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.system.CPUUtilizationRecord;
public class LogConsumer extends AbstractFilterPlugin {
public static final String INPUT_PORT_NAME = "newMonitoringRecord";

public  LogConsumer(final Configuration configuration, final IProjectContext projectContext){
super(configuration, projectContext);
}
@InputPort(name = LogConsumer.INPUT_PORT_NAME, eventTypes = {IMonitoringRecord.class})
public  void newMonitoringRecord(final Object record) {
if (record instanceof IMonitoringRecord)
{
if (record instanceof CPUUtilizationRecord)
{
CPUUtilizationRecord cpu = (CPUUtilizationRecord) record;
System.out.println(cpu.getHostname() + cpu.getTotalUtilization());
}
IMonitoringRecord monitoringRecord = (IMonitoringRecord) record;
MemoryLogStorage.storeRecord(monitoringRecord);
}
else
{
throw new  IllegalStateException("Unknown monitoring result type");
}
}

@Override
public  Configuration getCurrentConfiguration() {
return new  Configuration();
}

}
