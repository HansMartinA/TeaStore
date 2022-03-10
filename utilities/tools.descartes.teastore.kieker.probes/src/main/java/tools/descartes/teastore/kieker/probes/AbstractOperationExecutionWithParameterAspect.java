package tools.descartes.teastore.kieker.probes;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import kieker.common.logging.Log;
import kieker.common.logging.LogFactory;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.core.registry.ControlFlowRegistry;
import kieker.monitoring.core.registry.SessionRegistry;
import kieker.monitoring.probe.aspectj.AbstractAspectJProbe;
import kieker.monitoring.timer.ITimeSource;
import tools.descartes.teastore.kieker.probes.records.OperationExecutionWithParametersRecord;
@Aspect
public abstract class AbstractOperationExecutionWithParameterAspect extends AbstractAspectJProbe {
private static final Log LOG = LogFactory.getLog(AbstractOperationExecutionWithParameterAspect.class);

private static final IMonitoringController CTRLINST = MonitoringController.getInstance();

private static final ITimeSource TIME = CTRLINST.getTimeSource();

private static final String VMNAME = CTRLINST.getHostname();

private static final ControlFlowRegistry CFREGISTRY = ControlFlowRegistry.INSTANCE;

private static final SessionRegistry SESSIONREGISTRY = SessionRegistry.INSTANCE;

@Pointcut
public abstract  void monitoredOperation() ;

@Around("monitoredOperation() && notWithinKieker()")
public  Object operation(final ProceedingJoinPoint thisJoinPoint)throws Throwable {
if (!CTRLINST.isMonitoringEnabled())
{
return thisJoinPoint.proceed();
}
final String signature = this.signatureToLongString(thisJoinPoint.getSignature());
if (!CTRLINST.isProbeActivated(signature))
{
return thisJoinPoint.proceed();
}
final boolean entrypoint;
final String hostname = VMNAME;
final String sessionId = SESSIONREGISTRY.recallThreadLocalSessionId();
final int eoi;
final int ess;
long traceId = CFREGISTRY.recallThreadLocalTraceId();
if (traceId ==  - 1)
{
entrypoint = true;
traceId = CFREGISTRY.getAndStoreUniqueThreadLocalTraceId();
CFREGISTRY.storeThreadLocalEOI(0);
CFREGISTRY.storeThreadLocalESS(1);
eoi = 0;
ess = 0;
}
else
{
entrypoint = false;
eoi = CFREGISTRY.incrementAndRecallThreadLocalEOI();
ess = CFREGISTRY.recallAndIncrementThreadLocalESS();
if ((eoi ==  - 1) || (ess ==  - 1))
{
LOG.error("eoi and/or ess have invalid values:" + " eoi == " + eoi + " ess == " + ess);
CTRLINST.terminateMonitoring();
}
}
final long tin = TIME.getTime();
Object retval = null;
try {
retval = thisJoinPoint.proceed();
}
finally {
final long tout = TIME.getTime();
if (System.getenv("flag").equals("true"))
{
logWithParameter(thisJoinPoint, signature, sessionId, traceId, tin, tout, hostname, eoi, ess, retval);
}
else
{
logWithoutParameters(thisJoinPoint, signature, sessionId, traceId, tin, tout, hostname, eoi, ess, retval);
}
if (entrypoint)
{
CFREGISTRY.unsetThreadLocalTraceId();
CFREGISTRY.unsetThreadLocalEOI();
CFREGISTRY.unsetThreadLocalESS();
}
else
{
CFREGISTRY.storeThreadLocalESS(ess);
}
}
return retval;
}

private  void logWithParameter(final ProceedingJoinPoint thisJoinPoint, String signature, String sessionId, long traceId, long tin, long tout, String hostname, int eoi, int ess, Object retval) {
final String[]  names = ((MethodSignature) thisJoinPoint.getSignature()).getParameterNames();
final Object[]  arguments = thisJoinPoint.getArgs();
final String[]  values = new String [arguments.length] ;
int i = 0;
for (final Object argument : arguments)
{
values[i] = parseObjectToString(argument);
if (argument instanceof java.util.Collection && !names[i].endsWith(".size()"))
names[i] = names[i] + ".size()";
i++;
}
Class<?> returnClass = ((MethodSignature) thisJoinPoint.getSignature()).getReturnType();
final String returnType;
final String returnValue;
if (returnClass.equals(Void.TYPE))
{
returnType = "void";
returnValue = "";
}
else
{
returnType = returnClass.getName();
returnValue = parseObjectToString(retval);
}
CTRLINST.newMonitoringRecord(new  OperationExecutionWithParametersRecord(signature, sessionId, traceId, tin, tout, hostname, eoi, ess, names, values, returnType, returnValue));
}

private  String parseObjectToString(Object argument) {
if (argument == null)
{
return "null";
}
if (argument instanceof java.util.Collection)
{
return String.valueOf(((java.util.Collection<?>) argument).size());
}
return argument.toString();
}

private  void logWithoutParameters(final ProceedingJoinPoint thisJoinPoint, String signature, String sessionId, long traceId, long tin, long tout, String hostname, int eoi, int ess, Object retval) {
CTRLINST.newMonitoringRecord(new  OperationExecutionRecord(signature, sessionId, traceId, tin, tout, hostname, eoi, ess));
}

}
