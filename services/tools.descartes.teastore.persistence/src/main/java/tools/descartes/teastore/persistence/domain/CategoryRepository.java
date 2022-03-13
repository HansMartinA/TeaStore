package tools.descartes.teastore.persistence.domain;

import javax.persistence.EntityManager;
import tools.descartes.teastore.persistence.repository.AbstractPersistenceRepository;
import tools.descartes.teastore.entities.Category;
public final class CategoryRepository extends AbstractPersistenceRepository<Category, PersistenceCategory> {
public static final CategoryRepository REPOSITORY = new  CategoryRepository();

private  CategoryRepository(){
}
@Override
public  long createEntity(Category entity) {
PersistenceCategory category = new  PersistenceCategory();
category.setName(entity.getName());
category.setDescription(entity.getDescription());
EntityManager em = getEM();
try {
em.getTransaction().begin();
em.persist(category);
em.getTransaction().commit();
}
finally {
em.close();
}
return category.getId();
}

@Override
public  boolean updateEntity(long id, Category entity) {
boolean found = false;
EntityManager em = getEM();
try {
em.getTransaction().begin();
PersistenceCategory category = em.find(getEntityClass(), id);
if (category != null)
{
category.setName(entity.getName());
category.setDescription(entity.getDescription());
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
protected  long getId(PersistenceCategory v) {
return v.getId();
}

@Override
protected  Class<PersistenceCategory> getEntityClass() {
return PersistenceCategory.class;
}

}
