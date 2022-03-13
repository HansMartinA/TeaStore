package tools.descartes.teastore.registryclient;

public enum Service {
PERSISTENCE ("tools.descartes.teastore.persistence"),
RECOMMENDER ("tools.descartes.teastore.recommender"),
AUTH ("tools.descartes.teastore.auth"),
WEBUI ("tools.descartes.teastore.webui"),
IMAGE ("tools.descartes.teastore.image"),
;

private String serviceName;

 Service(String serviceName){
this.serviceName = serviceName;
}
public  String getServiceName() {
return serviceName;
}

}
