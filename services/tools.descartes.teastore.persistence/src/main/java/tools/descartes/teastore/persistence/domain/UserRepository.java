package tools.descartes.teastore.persistence.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tools.descartes.teastore.persistence.repository.AbstractPersistenceRepository;
import tools.descartes.teastore.entities.User;
public final class UserRepository extends AbstractPersistenceRepository<User, PersistenceUser> {
public static final UserRepository REPOSITORY = new  UserRepository();

private  UserRepository(){
}
@Override
public  long createEntity(User entity) {
PersistenceUser user = new  PersistenceUser();
user.setUserName(entity.getUserName());
user.setPassword(entity.getPassword());
user.setRealName(entity.getRealName());
user.setEmail(entity.getEmail());
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceUser existing = getUserByName(entity.getUserName());
if (existing == null)
{
em.persist(user);
}
else
{
user.setId( - 1L);
}
em.getTransaction().commit();
}
finally {
em.close();
}
return user.getId();
}

@Override
public  boolean updateEntity(long id, User entity) {
boolean found = false;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceUser user = em.find(getEntityClass(), id);
if (user != null)
{
user.setUserName(entity.getUserName());
user.setPassword(entity.getPassword());
user.setRealName(entity.getRealName());
user.setEmail(entity.getEmail());
found = true;
}
em.getTransaction().commit();
}
finally {
em.close();
}
return found;
}

@Override
protected  long getId(PersistenceUser v) {
return v.getId();
}

@Override
protected  Class<PersistenceUser> getEntityClass() {
return PersistenceUser.class;
}

public  PersistenceUser getUserByName(String userName) {
EntityManager em = getEM();
TypedQuery<PersistenceUser> allMatchesQuery = em.createQuery("SELECT u FROM " + getEntityClass().getName() + " u WHERE u.userName = :name", getEntityClass()).setMaxResults(1);
allMatchesQuery.setParameter("name", userName);
List<PersistenceUser> entities = allMatchesQuery.getResultList();
if (entities == null || entities.isEmpty())
{
return null;
}
return entities.get(0);
}

}
