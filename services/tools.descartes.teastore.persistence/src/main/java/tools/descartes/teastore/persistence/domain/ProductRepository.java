package tools.descartes.teastore.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tools.descartes.teastore.persistence.repository.AbstractPersistenceRepository;
import tools.descartes.teastore.entities.Product;
public final class ProductRepository extends AbstractPersistenceRepository<Product, PersistenceProduct> {
public static final ProductRepository REPOSITORY = new  ProductRepository();

private  ProductRepository(){
}
@Override
public  long createEntity(Product entity) {
PersistenceProduct product = new  PersistenceProduct();
product.setName(entity.getName());
product.setDescription(entity.getDescription());
product.setListPriceInCents(entity.getListPriceInCents());
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceCategory cat = em.find(PersistenceCategory.class, entity.getCategoryId());
if (cat != null)
{
product.setCategory(cat);
em.persist(product);
}
else
{
product.setId( - 1L);
}
em.getTransaction().commit();
}
finally {
em.close();
}
return product.getId();
}

@Override
public  boolean updateEntity(long id, Product entity) {
boolean found = false;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceProduct product = em.find(getEntityClass(), id);
if (product != null)
{
product.setName(entity.getName());
product.setDescription(entity.getDescription());
product.setListPriceInCents(entity.getListPriceInCents());
found = true;
}
em.getTransaction().commit();
}
finally {
em.close();
}
return found;
}

public  List<PersistenceProduct> getAllEntities(long categoryId, int start, int limit) {
List<PersistenceProduct> entities = null;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceCategory cat = em.find(PersistenceCategory.class, categoryId);
if (cat != null)
{
TypedQuery<PersistenceProduct> allMatchesQuery = em.createQuery("SELECT u FROM " + getEntityClass().getName() + " u WHERE u.category = :cat", getEntityClass());
allMatchesQuery.setParameter("cat", cat);
entities = resultsWithStartAndLimit(em, allMatchesQuery, start, limit);
}
em.getTransaction().commit();
}
finally {
em.close();
}
if (entities == null)
{
return new  ArrayList<PersistenceProduct>();
}
return entities;
}

public  long getProductCount(long categoryId) {
long count =  - 1;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceCategory cat = em.find(PersistenceCategory.class, categoryId);
if (cat != null)
{
TypedQuery<Long> allMatchesQuery = em.createQuery("SELECT COUNT(u) FROM " + getEntityClass().getName() + " u WHERE u.category = :cat", Long.class);
allMatchesQuery.setParameter("cat", cat);
Long countResult = allMatchesQuery.getSingleResult();
if (countResult != null)
{
count = countResult;
}
}
em.getTransaction().commit();
}
finally {
em.close();
}
return count;
}

@Override
protected  long getId(PersistenceProduct v) {
return v.getId();
}

@Override
protected  Class<PersistenceProduct> getEntityClass() {
return PersistenceProduct.class;
}

}
