package tools.descartes.teastore.registryclient.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.registryclient.util.RESTClient;
import tools.descartes.teastore.registryclient.util.TimeoutException;
public final class NonBalancedCRUDOperations {
private static final Logger LOG = LoggerFactory.getLogger(NonBalancedCRUDOperations.class);

private  NonBalancedCRUDOperations(){
}
public static <T>  long sendEntityForCreation(RESTClient<T> client, T entity)throws NotFoundException, TimeoutException {
Response response = ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget()).post(Entity.entity(entity, MediaType.APPLICATION_JSON), Response.class));
long id =  - 1L;
if (response != null && response.getStatus() == 201)
{
id = 0L;
try {
id = response.readEntity(Long.class);
}
catch(ProcessingException e){
LOG.warn("Response did not conform to expected message type. Expected a Long ID.");
}
}
else
if (response != null)
{
response.bufferEntity();
}
if (response.getStatus() == Status.NOT_FOUND.getStatusCode())
{
throw new  NotFoundException();
}
else
if (response.getStatus() == Status.REQUEST_TIMEOUT.getStatusCode())
{
throw new  TimeoutException();
}
return id;
}

public static <T>  boolean sendEntityForUpdate(RESTClient<T> client, long id, T entity)throws NotFoundException, TimeoutException {
Response response = ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path(String.valueOf(id))).put(Entity.entity(entity, MediaType.APPLICATION_JSON), Response.class));
if (response != null)
{
response.bufferEntity();
}
if (response.getStatus() == Status.NOT_FOUND.getStatusCode())
{
throw new  NotFoundException();
}
else
if (response.getStatus() == Status.REQUEST_TIMEOUT.getStatusCode())
{
throw new  TimeoutException();
}
if (response != null && response.getStatus() == 200)
{
return true;
}
return false;
}

public static <T>  boolean deleteEntity(RESTClient<T> client, long id)throws NotFoundException, TimeoutException {
Response response = ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path(String.valueOf(id))).delete());
if (response != null)
{
response.bufferEntity();
}
if (response != null && response.getStatus() == 200)
{
return true;
}
if (response.getStatus() == Status.NOT_FOUND.getStatusCode())
{
throw new  NotFoundException();
}
else
if (response.getStatus() == Status.REQUEST_TIMEOUT.getStatusCode())
{
throw new  TimeoutException();
}
return false;
}

public static <T>  T getEntity(RESTClient<T> client, long id)throws NotFoundException, TimeoutException {
Response response = ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path(String.valueOf(id))).get());
T entity = null;
if (response != null && response.getStatus() < 400)
{
try {
entity = response.readEntity(client.getEntityClass());
}
catch(NullPointerException | ProcessingException e){
LOG.warn("Response did not conform to expected entity type.");
}
}
else
if (response != null)
{
response.bufferEntity();
}
if (response.getStatus() == Status.NOT_FOUND.getStatusCode())
{
throw new  NotFoundException();
}
else
if (response.getStatus() == Status.REQUEST_TIMEOUT.getStatusCode())
{
throw new  TimeoutException();
}
return entity;
}

public static <T>  List<T> getEntities(RESTClient<T> client, int startIndex, int limit)throws NotFoundException, TimeoutException {
WebTarget target = client.getEndpointTarget();
if (startIndex >= 0)
{
target = target.queryParam("start", startIndex);
}
if (limit >= 0)
{
target = target.queryParam("max", limit);
}
GenericType<List<T>> listType = client.getGenericListType();
Response response = ResponseWrapper.wrap(HttpWrapper.wrap(target).get());
List<T> entities = new  ArrayList<T>();
if (response != null && response.getStatus() == 200)
{
try {
entities = response.readEntity(listType);
}
catch(ProcessingException e){
LOG.warn("Response did not conform to expected entity type. List expected.");
}
}
else
if (response != null)
{
response.bufferEntity();
}
if (response.getStatus() == Status.NOT_FOUND.getStatusCode())
{
throw new  NotFoundException();
}
else
if (response.getStatus() == Status.REQUEST_TIMEOUT.getStatusCode())
{
throw new  TimeoutException();
}
return entities;
}

public static <T>  List<T> getEntities(RESTClient<T> client, String filterURI, long filterId, int startIndex, int limit)throws NotFoundException, TimeoutException {
WebTarget target = client.getEndpointTarget().path(filterURI).path(String.valueOf(filterId));
if (startIndex >= 0)
{
target = target.queryParam("start", startIndex);
}
if (limit >= 0)
{
target = target.queryParam("max", limit);
}
Response response = ResponseWrapper.wrap(HttpWrapper.wrap(target).get());
List<T> entities = new  ArrayList<T>();
if (response != null && response.getStatus() == 200)
{
try {
entities = response.readEntity(client.getGenericListType());
}
catch(ProcessingException e){
e.printStackTrace();
LOG.warn("Response did not conform to expected entity type. List expected.");
}
}
else
if (response != null)
{
response.bufferEntity();
}
if (response.getStatus() == Status.NOT_FOUND.getStatusCode())
{
throw new  NotFoundException();
}
else
if (response.getStatus() == Status.REQUEST_TIMEOUT.getStatusCode())
{
throw new  TimeoutException();
}
return entities;
}

public static <T>  T getEntityWithProperty(RESTClient<T> client, String propertyURI, String propertyValue)throws NotFoundException, TimeoutException {
WebTarget target = client.getEndpointTarget().path(propertyURI).path(propertyValue);
Response response = ResponseWrapper.wrap(HttpWrapper.wrap(target).get());
T entity = null;
if (response != null && response.getStatus() < 400)
{
try {
entity = response.readEntity(client.getEntityClass());
}
catch(NullPointerException | ProcessingException e){
}
}
else
if (response != null)
{
response.bufferEntity();
}
if (response.getStatus() == Status.NOT_FOUND.getStatusCode())
{
throw new  NotFoundException();
}
else
if (response.getStatus() == Status.REQUEST_TIMEOUT.getStatusCode())
{
throw new  TimeoutException();
}
return entity;
}

}
