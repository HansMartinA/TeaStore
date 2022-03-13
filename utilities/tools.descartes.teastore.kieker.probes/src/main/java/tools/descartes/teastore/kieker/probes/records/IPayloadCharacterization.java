package tools.descartes.teastore.kieker.probes.records;

import kieker.common.record.IMonitoringRecord;
public interface IPayloadCharacterization extends IMonitoringRecord {
public  String[]  getParameterTypes() ;

public  String[]  getParameterValues() ;

public  String getReturnType() ;

public  String getReturnValue() ;

}
