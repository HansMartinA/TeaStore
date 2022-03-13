package tools.descartes.teastore.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PostRemove;
import tools.descartes.teastore.persistence.repository.CacheManager;
import tools.descartes.teastore.entities.Category;
@Entity
public class PersistenceCategory extends Category {
@Id
@GeneratedValue
private long id;

@Column(length = 100)
private String name;

@Lob
private String description;

@OneToMany(mappedBy = "category", orphanRemoval = true, cascade = {CascadeType.ALL})
private List<PersistenceProduct> products;

 PersistenceCategory(){
super();
products = new  ArrayList<>();
}
@PostRemove
private  void clearCaches() {
CacheManager.MANAGER.clearCache(PersistenceProduct.class);
CacheManager.MANAGER.clearRemoteCache(PersistenceCategory.class);
}

@Override
public  long getId() {
return id;
}

@Override
public  void setId(long id) {
this.id = id;
}

@Override
public  String getName() {
return name;
}

@Override
public  void setName(String name) {
this.name = name;
}

@Override
public  String getDescription() {
return description;
}

@Override
public  void setDescription(String description) {
this.description = description;
}

public  List<PersistenceProduct> getProducts() {
return products;
}

}
