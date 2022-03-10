package tools.descartes.teastore.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostRemove;
import tools.descartes.teastore.persistence.repository.CacheManager;
import tools.descartes.teastore.entities.Product;
@Entity
public class PersistenceProduct extends Product {
@Id
@GeneratedValue
private long id;

@Column(length = 100)
private String name;

@Lob
private String description;

private long listPriceInCents;

@ManyToOne
private PersistenceCategory category;

@OneToMany(mappedBy = "product", orphanRemoval = true, cascade = {CascadeType.ALL})
private List<PersistenceOrderItem> orderItems;

@PostRemove
private  void clearCaches() {
CacheManager.MANAGER.clearCache(PersistenceCategory.class);
CacheManager.MANAGER.clearRemoteCache(PersistenceProduct.class);
}

 PersistenceProduct(){
super();
orderItems = new  ArrayList<PersistenceOrderItem>();
}
@Override
public  void setId(long id) {
this.id = id;
}

@Override
public  long getId() {
return id;
}

@Override
public  long getCategoryId() {
return category.getId();
}

@Override
public  void setCategoryId(long categoryId) {
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

@Override
public  long getListPriceInCents() {
return listPriceInCents;
}

@Override
public  void setListPriceInCents(long listPriceInCents) {
this.listPriceInCents = listPriceInCents;
}

public  PersistenceCategory getCategory() {
return category;
}

public  void setCategory(PersistenceCategory category) {
this.category = category;
}

public  List<PersistenceOrderItem> getOrderItems() {
return orderItems;
}

}
