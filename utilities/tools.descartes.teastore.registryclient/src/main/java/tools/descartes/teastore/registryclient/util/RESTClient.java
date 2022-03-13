package tools.descartes.teastore.registryclient.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;
public class RESTClient<T>  {
private static final int DEFAULT_CONNECT_TIMEOUT = 400;

private static final int DEFAULT_READ_TIMEOUT = 3000;

public static final String DEFAULT_REST_APPLICATION = "rest";

private static int readTimeout = DEFAULT_READ_TIMEOUT;

private static int connectTimeout = DEFAULT_CONNECT_TIMEOUT;

private String applicationURI;

private String endpointURI;

private Client client;

private WebTarget service;

private Class<T> entityClass;

private ParameterizedType parameterizedGenericType;

private GenericType<List<T>> genericListType;

public  RESTClient(String hostURL, String application, String endpoint, final Class<T> entityClass){
if (!hostURL.endsWith("/"))
{
hostURL += "/";
}
if (!hostURL.contains("://"))
{
hostURL = "http://" + hostURL;
}
ClientConfig config = new  ClientConfig();
config.property(ClientProperties.CONNECT_TIMEOUT, connectTimeout);
config.property(ClientProperties.READ_TIMEOUT, readTimeout);
config.connectorProvider(new  GrizzlyConnectorProvider());
client = ClientBuilder.newClient(config);
service = client.target(UriBuilder.fromUri(hostURL).build());
applicationURI = application;
endpointURI = endpoint;
this.entityClass = entityClass;
parameterizedGenericType = new  ParameterizedType(){
public  Type[]  getActualTypeArguments() {
return new Type[]  {entityClass};
}

public  Type getRawType() {
return List.class;
}

public  Type getOwnerType() {
return List.class;
}

}
;
genericListType = new  GenericType<List<T>>(parameterizedGenericType){
}
;
}
public static  void setGlobalReadTimeout(int readTimeout) {
RESTClient.readTimeout = readTimeout;
}

public static  void setGlobalConnectTimeout(int connectTimeout) {
RESTClient.connectTimeout = connectTimeout;
}

public  GenericType<List<T>> getGenericListType() {
return genericListType;
}

public  Class<T> getEntityClass() {
return entityClass;
}

public  WebTarget getService() {
return service;
}

public  WebTarget getEndpointTarget() {
return service.path(applicationURI).path(endpointURI);
}

public  String getEndpointURI() {
return endpointURI;
}

public  String getApplicationURI() {
return applicationURI;
}

}
