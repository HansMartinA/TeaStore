package tools.descartes.teastore.persistence.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tools.descartes.teastore.persistence.domain.UserRepository;
import tools.descartes.teastore.persistence.repository.DataGenerator;
import tools.descartes.teastore.registryclient.util.AbstractCRUDEndpoint;
import tools.descartes.teastore.entities.User;
@Path("users")
public class UserEndpoint extends AbstractCRUDEndpoint<User> {
@Override
protected  long createEntity(final User category) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return  - 1L;
}
try {
return UserRepository.REPOSITORY.createEntity(category);
}
catch(Exception e){
return  - 1L;
}
}

@Override
protected  User findEntityById(final long id) {
User user = UserRepository.REPOSITORY.getEntity(id);
if (user == null)
{
return null;
}
return new  User(user);
}

@Override
protected  List<User> listAllEntities(final int startIndex, final int maxResultCount) {
List<User> users = new  ArrayList<User>();
for (User u : UserRepository.REPOSITORY.getAllEntities(startIndex, maxResultCount))
{
users.add(new  User(u));
}
return users;
}

@Override
protected  boolean updateEntity(long id, User category) {
return UserRepository.REPOSITORY.updateEntity(id, category);
}

@Override
protected  boolean deleteEntity(long id) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return false;
}
return UserRepository.REPOSITORY.removeEntity(id);
}

@GET
@Path("name/{name}")
public  Response findById(@PathParam("name")
final String name) {
cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController threadMonitoringController = cipm.consistency.bridge.monitoring.controller.ThreadMonitoringController.getInstance();
cipm.consistency.bridge.monitoring.controller.ServiceParameters monitoringServiceParameters = new  cipm.consistency.bridge.monitoring.controller.ServiceParameters();
monitoringServiceParameters.addValue("name", name);
threadMonitoringController.enterService("_8n_eMAd_Eeyp7eds7yI7tA", this, monitoringServiceParameters);
try {
threadMonitoringController.enterInternalAction("_0o6tMApQEeycOb9KY39UQw", "_oro4gG3fEdy4YaaT-RYrLQ");
if (name == null || name.isEmpty())
{
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16304102919720034860998948446265 = Response.status(Status.NOT_FOUND).build();
threadMonitoringController.exitInternalAction("_0o6tMApQEeycOb9KY39UQw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors16304102919720034860998948446265;
}
threadMonitoringController.enterInternalAction("_0pAz5gpQEeycOb9KY39UQw", "_oro4gG3fEdy4YaaT-RYrLQ");
User entity = UserRepository.REPOSITORY.getUserByName(name);
threadMonitoringController.exitInternalAction("_0pAz5gpQEeycOb9KY39UQw", "_oro4gG3fEdy4YaaT-RYrLQ");
threadMonitoringController.enterInternalAction("_0pCB8ApQEeycOb9KY39UQw", "_oro4gG3fEdy4YaaT-RYrLQ");
if (entity == null)
{
return Response.status(Status.NOT_FOUND).build();
}
Response longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630410291972008350823675214606 = Response.ok(new  User(entity)).build();
threadMonitoringController.exitInternalAction("_0pCB8ApQEeycOb9KY39UQw", "_oro4gG3fEdy4YaaT-RYrLQ");
return longAndUniqueNameToAvoidDuplicationsAndCompilationErrors1630410291972008350823675214606;
}
finally {
threadMonitoringController.exitService("_8n_eMAd_Eeyp7eds7yI7tA");
}
}

}
