package tools.descartes.teastore.kieker.probes.records;

import java.nio.BufferOverflowException;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.common.record.io.IValueDeserializer;
import kieker.common.record.io.IValueSerializer;
import kieker.common.util.registry.IRegistry;
public class OperationExecutionWithParametersRecord extends OperationExecutionRecord implements IPayloadCharacterization {
private static final long serialVersionUID = 5027368663979062260L;

private static final int SIZE = TYPE_SIZE_STRING + TYPE_SIZE_STRING + TYPE_SIZE_LONG + TYPE_SIZE_LONG + TYPE_SIZE_LONG + TYPE_SIZE_STRING + TYPE_SIZE_INT + TYPE_SIZE_INT + TYPE_SIZE_STRING + TYPE_SIZE_STRING + TYPE_SIZE_STRING + TYPE_SIZE_STRING;

private static final Class<?>[]  TYPES = {String.class, String.class, long.class, long.class, long.class, String.class, int.class, int.class, String[] .class, String[] .class, String.class, String.class};

private static final String[]  PROPERTY_NAMES = {"operationSignature", "sessionId", "traceId", "tin", "tout", "hostname", "eoi", "ess", "parameterTypes", "parameterValues", "returnType", "returnValue"};

private final String[]  parameterTypes;

private final String[]  parameterValues;

private final String returnType;

private final String returnValue;

public  OperationExecutionWithParametersRecord(final String operationSignature, final String sessionId, final long traceId, final long tin, final long tout, final String hostname, final int eoi, final int ess, final String[]  parameterTypes, final String[]  parameterValues, final String returnType, final String returnValue){
super(operationSignature, sessionId, traceId, tin, tout, hostname, eoi, ess);
this.parameterTypes = parameterTypes;
this.parameterValues = parameterValues;
this.returnType = returnType;
this.returnValue = returnValue;
}
public  OperationExecutionWithParametersRecord(final IValueDeserializer deserializer){
super(deserializer);
int parametersSize0 = deserializer.getInt();
this.parameterTypes = new String [parametersSize0] ;
for (int i0 = 0 ; i0 < parametersSize0 ; i0++)
{
this.parameterTypes[i0] = deserializer.getString();
}
int valuesSize0 = deserializer.getInt();
this.parameterValues = new String [valuesSize0] ;
for (int i0 = 0 ; i0 < valuesSize0 ; i0++)
{
this.parameterValues[i0] = deserializer.getString();
}
this.returnType = deserializer.getString();
this.returnValue = deserializer.getString();
}
@Override
@Deprecated
public  Object[]  toArray() {
throw new  UnsupportedOperationException();
}

@Override
@Deprecated
public  void registerStrings(final IRegistry<String> stringRegistry) {
throw new  UnsupportedOperationException();
}

@Override
public  void serialize(final IValueSerializer serializer)throws BufferOverflowException {
super.serialize(serializer);
int parametersSize0 = this.getParameterTypes().length;
serializer.putInt(parametersSize0);
for (int i0 = 0 ; i0 < parametersSize0 ; i0++)
{
serializer.putString(this.getParameterTypes()[i0]);
}
int valuesSize0 = this.getParameterValues().length;
serializer.putInt(valuesSize0);
for (int i0 = 0 ; i0 < valuesSize0 ; i0++)
{
serializer.putString(this.getParameterValues()[i0]);
}
serializer.putString(this.getReturnType());
serializer.putString(this.getReturnValue());
}

@Override
public  Class<?>[]  getValueTypes() {
return TYPES;
}

@Override
public  String[]  getValueNames() {
return PROPERTY_NAMES;
}

@Override
public  int getSize() {
return SIZE;
}

@Deprecated
@Override
public  void initFromArray(final Object[]  values) {
throw new  UnsupportedOperationException();
}

@Override
public  boolean equals(final Object obj) {
if (obj == null)
{
return false;
}
if (obj == this)
{
return true;
}
if (obj.getClass() != this.getClass())
{
return false;
}
final OperationExecutionWithParametersRecord castedRecord = (OperationExecutionWithParametersRecord) obj;
if (this.getLoggingTimestamp() != castedRecord.getLoggingTimestamp())
{
return false;
}
if (!this.getOperationSignature().equals(castedRecord.getOperationSignature()))
{
return false;
}
if (!this.getSessionId().equals(castedRecord.getSessionId()))
{
return false;
}
if (this.getTraceId() != castedRecord.getTraceId())
{
return false;
}
if (this.getTin() != castedRecord.getTin())
{
return false;
}
if (this.getTout() != castedRecord.getTout())
{
return false;
}
if (!this.getHostname().equals(castedRecord.getHostname()))
{
return false;
}
if (this.getEoi() != castedRecord.getEoi())
{
return false;
}
if (this.getEss() != castedRecord.getEss())
{
return false;
}
if (!this.getReturnType().equals(castedRecord.getReturnType()))
{
return false;
}
if (!this.getReturnValue().equals(castedRecord.getReturnValue()))
{
return false;
}
int parametersSize0 = this.getParameterTypes().length;
if (parametersSize0 != castedRecord.getParameterTypes().length)
{
return false;
}
for (int i0 = 0 ; i0 < parametersSize0 ; i0++)
{
if (!this.getParameterTypes()[i0].equals(castedRecord.getParameterTypes()[i0]))
{
return false;
}
}
int valuesSize0 = this.getParameterValues().length;
if (valuesSize0 != castedRecord.getParameterValues().length)
{
return false;
}
for (int i0 = 0 ; i0 < valuesSize0 ; i0++)
{
if (!this.getParameterValues()[i0].equals(castedRecord.getParameterValues()[i0]))
{
return false;
}
}
return true;
}

public final  String[]  getParameterTypes() {
return this.parameterTypes;
}

public final  String[]  getParameterValues() {
return this.parameterValues;
}

public  String getReturnType() {
return this.returnType;
}

public  String getReturnValue() {
return this.returnValue;
}

}
