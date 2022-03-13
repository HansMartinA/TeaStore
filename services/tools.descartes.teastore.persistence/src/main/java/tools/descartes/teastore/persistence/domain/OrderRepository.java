package tools.descartes.teastore.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tools.descartes.teastore.persistence.repository.AbstractPersistenceRepository;
import tools.descartes.teastore.entities.Order;
public final class OrderRepository extends AbstractPersistenceRepository<Order, PersistenceOrder> {
public static final OrderRepository REPOSITORY = new  OrderRepository();

private  OrderRepository(){
}
@Override
public  long createEntity(Order entity) {
PersistenceOrder order = new  PersistenceOrder();
order.setTime(entity.getTime());
order.setTotalPriceInCents(entity.getTotalPriceInCents());
order.setAddressName(entity.getAddressName());
order.setAddress1(entity.getAddress1());
order.setAddress2(entity.getAddress2());
order.setCreditCardCompany(entity.getCreditCardCompany());
order.setCreditCardNumber(entity.getCreditCardNumber());
order.setCreditCardExpiryDate(entity.getCreditCardExpiryDate());
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceUser user = em.find(PersistenceUser.class, entity.getUserId());
if (user != null)
{
order.setUser(user);
em.persist(order);
}
else
{
order.setId( - 1L);
}
em.getTransaction().commit();
}
finally {
em.close();
}
return order.getId();
}

@Override
public  boolean updateEntity(long id, Order entity) {
boolean found = false;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceOrder order = em.find(getEntityClass(), id);
if (order != null)
{
order.setTime(entity.getTime());
order.setTotalPriceInCents(entity.getTotalPriceInCents());
order.setAddressName(entity.getAddressName());
order.setAddress1(entity.getAddress1());
order.setAddress2(entity.getAddress2());
order.setCreditCardCompany(entity.getCreditCardCompany());
order.setCreditCardNumber(entity.getCreditCardNumber());
order.setCreditCardExpiryDate(entity.getCreditCardExpiryDate());
found = true;
}
em.getTransaction().commit();
}
finally {
em.close();
}
return found;
}

public  List<PersistenceOrder> getAllEntitiesWithUser(long userId, int start, int limit) {
List<PersistenceOrder> entities = null;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceUser user = em.find(PersistenceUser.class, userId);
if (user != null)
{
TypedQuery<PersistenceOrder> allMatchesQuery = em.createQuery("SELECT u FROM " + getEntityClass().getName() + " u WHERE u.user = :user", getEntityClass());
allMatchesQuery.setParameter("user", user);
entities = resultsWithStartAndLimit(em, allMatchesQuery, start, limit);
}
em.getTransaction().commit();
}
finally {
em.close();
}
if (entities == null)
{
return new  ArrayList<PersistenceOrder>();
}
return entities;
}

@Override
protected  long getId(PersistenceOrder v) {
return v.getId();
}

@Override
protected  Class<PersistenceOrder> getEntityClass() {
return PersistenceOrder.class;
}

}
