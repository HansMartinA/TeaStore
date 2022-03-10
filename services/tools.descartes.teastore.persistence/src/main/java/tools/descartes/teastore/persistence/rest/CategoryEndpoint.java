package tools.descartes.teastore.persistence.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import tools.descartes.teastore.persistence.domain.CategoryRepository;
import tools.descartes.teastore.persistence.repository.DataGenerator;
import tools.descartes.teastore.registryclient.util.AbstractCRUDEndpoint;
import tools.descartes.teastore.entities.Category;
@Path("categories")
public class CategoryEndpoint extends AbstractCRUDEndpoint<Category> {
@Override
protected  long createEntity(final Category category) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return  - 1L;
}
return CategoryRepository.REPOSITORY.createEntity(category);
}

@Override
protected  Category findEntityById(final long id) {
Category category = CategoryRepository.REPOSITORY.getEntity(id);
if (category == null)
{
return null;
}
return new  Category(category);
}

@Override
protected  List<Category> listAllEntities(final int startIndex, final int maxResultCount) {
List<Category> categories = new  ArrayList<Category>();
for (Category c : CategoryRepository.REPOSITORY.getAllEntities(startIndex, maxResultCount))
{
categories.add(new  Category(c));
}
return categories;
}

@Override
protected  boolean updateEntity(long id, Category category) {
return CategoryRepository.REPOSITORY.updateEntity(id, category);
}

@Override
protected  boolean deleteEntity(long id) {
if (DataGenerator.GENERATOR.isMaintenanceMode())
{
return false;
}
return CategoryRepository.REPOSITORY.removeEntity(id);
}

}
