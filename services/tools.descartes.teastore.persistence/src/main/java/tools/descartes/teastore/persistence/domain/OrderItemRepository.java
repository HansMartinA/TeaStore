package tools.descartes.teastore.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tools.descartes.teastore.persistence.repository.AbstractPersistenceRepository;
import tools.descartes.teastore.entities.OrderItem;
public final class OrderItemRepository extends AbstractPersistenceRepository<OrderItem, PersistenceOrderItem> {
public static final OrderItemRepository REPOSITORY = new  OrderItemRepository();

private  OrderItemRepository(){
}
@Override
public  long createEntity(OrderItem entity) {
PersistenceOrderItem item = new  PersistenceOrderItem();
item.setQuantity(entity.getQuantity());
item.setUnitPriceInCents(entity.getUnitPriceInCents());
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceProduct prod = em.find(PersistenceProduct.class, entity.getProductId());
PersistenceOrder order = em.find(PersistenceOrder.class, entity.getOrderId());
if (prod != null && order != null)
{
item.setProduct(prod);
item.setOrder(order);
em.persist(item);
}
else
{
item.setId( - 1L);
}
em.getTransaction().commit();
}
finally {
em.close();
}
return item.getId();
}

@Override
public  boolean updateEntity(long id, OrderItem entity) {
boolean found = false;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceOrderItem item = em.find(getEntityClass(), id);
if (item != null)
{
item.setQuantity(entity.getQuantity());
item.setUnitPriceInCents(entity.getUnitPriceInCents());
found = true;
}
em.getTransaction().commit();
}
finally {
em.close();
}
return found;
}

public  List<PersistenceOrderItem> getAllEntitiesWithProduct(long productId, int start, int limit) {
List<PersistenceOrderItem> entities = null;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceProduct prod = em.find(PersistenceProduct.class, productId);
if (prod != null)
{
TypedQuery<PersistenceOrderItem> allMatchesQuery = em.createQuery("SELECT u FROM " + getEntityClass().getName() + " u WHERE u.product = :prod", getEntityClass());
allMatchesQuery.setParameter("prod", prod);
entities = resultsWithStartAndLimit(em, allMatchesQuery, start, limit);
}
em.getTransaction().commit();
}
finally {
em.close();
}
if (entities == null)
{
return new  ArrayList<PersistenceOrderItem>();
}
return entities;
}

public  List<PersistenceOrderItem> getAllEntitiesWithOrder(long orderId, int start, int limit) {
List<PersistenceOrderItem> entities = null;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceOrder order = em.find(PersistenceOrder.class, orderId);
if (order != null)
{
TypedQuery<PersistenceOrderItem> allMatchesQuery = em.createQuery("SELECT u FROM " + getEntityClass().getName() + " u WHERE u.order = :order", getEntityClass());
allMatchesQuery.setParameter("order", order);
entities = resultsWithStartAndLimit(em, allMatchesQuery, start, limit);
}
em.getTransaction().commit();
}
finally {
em.close();
}
if (entities == null)
{
return new  ArrayList<PersistenceOrderItem>();
}
return entities;
}

@Override
protected  long getId(PersistenceOrderItem v) {
return v.getId();
}

@Override
protected  Class<PersistenceOrderItem> getEntityClass() {
return PersistenceOrderItem.class;
}

}
