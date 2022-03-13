package tools.descartes.teastore.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
public abstract class AbstractPersistenceRepository<E, P extends E>  {
protected abstract  long getId(P p) ;

protected abstract  Class<P> getEntityClass() ;

public  EntityManagerFactory getEMF() {
return EMFManager.getEMF();
}

protected  EntityManager getEM() {
return getEMF().createEntityManager();
}

public abstract  long createEntity(E entity) ;

public abstract  boolean updateEntity(long id, E entity) ;

public  P getEntity(long id) {
P instance = null;
EntityManager em = getEM();
try {
instance = em.find(getEntityClass(), id);
}
finally {
em.close();
}
return instance;
}

public  List<P> getAllEntities() {
return getAllEntities( - 1,  - 1);
}

public  List<P> getAllEntities(int start, int limit) {
EntityManager em = getEM();
List<P> entities = null;
try {
TypedQuery<P> allMatchesQuery = em.createQuery("SELECT u FROM " + getEntityClass().getName() + " u", getEntityClass());
if (start >= 0)
{
allMatchesQuery = allMatchesQuery.setFirstResult(start);
}
if (limit >= 0)
{
allMatchesQuery = allMatchesQuery.setMaxResults(limit);
}
entities = allMatchesQuery.getResultList();
}
finally {
em.close();
}
if (entities == null)
{
entities = new  ArrayList<P>();
}
return entities;
}

public  boolean removeEntity(long id) {
boolean found = false;
EntityManager em = getEM();
try {
em.getTransaction().begin();
P entity = em.find(getEntityClass(), id);
if (entity != null)
{
em.remove(entity);
found = true;
}
em.getTransaction().commit();
}
finally {
em.close();
}
return found;
}

protected  List<P> resultsWithStartAndLimit(EntityManager em, TypedQuery<P> query, int start, int limit) {
if (start >= 0)
{
query.setFirstResult(start);
}
if (limit >= 0)
{
query = query.setMaxResults(limit);
}
return query.getResultList();
}

}
