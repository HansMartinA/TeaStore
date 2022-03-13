package tools.descartes.teastore.registryclient.util;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
@Path("abstract")
@Produces({"application/json"})
@Consumes({"application/json"})
public abstract class AbstractCRUDEndpoint<T>  {
@POST
public  Response create(final T entity) {
long id = createEntity(entity);
return Response.created(UriBuilder.fromResource(AbstractCRUDEndpoint.class).path(String.valueOf(id)).build()).entity(id).build();
}

protected abstract  long createEntity(final T entity) ;

@GET
@Path("/{id:[0-9][0-9]*}")
public  Response findById(@PathParam("id")
final Long id) {
if (id == null)
{
return Response.status(Status.NOT_FOUND).build();
}
T entity = findEntityById(id);
if (entity == null)
{
return Response.status(Status.NOT_FOUND).build();
}
return Response.ok(entity).build();
}

protected abstract  T findEntityById(final long id) ;

@GET
public  List<T> listAll(@QueryParam("start")
final Integer startPosition, @QueryParam("max")
final Integer maxResult) {
final List<T> entities = listAllEntities(parseIntQueryParam(startPosition), parseIntQueryParam(maxResult));
return entities;
}

protected abstract  List<T> listAllEntities(final int startIndex, final int maxResultCount) ;

@PUT
@Path("/{id:[0-9][0-9]*}")
public  Response update(@PathParam("id")
Long id, final T entity) {
boolean updated = false;
if (id != null && entity != null)
{
updated = updateEntity(id, entity);
}
if (updated)
{
return Response.ok().build();
}
else
{
return Response.status(Status.NOT_FOUND).build();
}
}

protected abstract  boolean updateEntity(long id, final T entity) ;

@DELETE
@Path("/{id:[0-9][0-9]*}")
public  Response deleteById(@PathParam("id")
final Long id) {
boolean deleted = deleteEntity(id);
if (deleted)
{
return Response.ok().build();
}
else
{
return Response.status(Status.NOT_FOUND).build();
}
}

protected abstract  boolean deleteEntity(long id) ;

protected  int parseIntQueryParam(Integer queryArg) {
if (queryArg != null)
{
return queryArg;
}
return  - 1;
}

}
