package tools.descartes.teastore.entities;

public class Product {
private long id;

private long categoryId;

private String name;

private String description;

private long listPriceInCents;

public  Product(){
}
public  Product(Product product){
setId(product.getId());
setCategoryId(product.getCategoryId());
setName(product.getName());
setDescription(product.getDescription());
setListPriceInCents(product.getListPriceInCents());
}
public  long getId() {
return id;
}

public  void setId(long id) {
this.id = id;
}

public  long getCategoryId() {
return categoryId;
}

public  void setCategoryId(long categoryId) {
this.categoryId = categoryId;
}

public  String getName() {
return name;
}

public  void setName(String name) {
this.name = name;
}

public  String getDescription() {
return description;
}

public  void setDescription(String description) {
this.description = description;
}

public  long getListPriceInCents() {
return listPriceInCents;
}

public  void setListPriceInCents(long listPriceInCents) {
this.listPriceInCents = listPriceInCents;
}

@Override
public  int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
result = prime * result + (int) (id ^ (id >>> 32));
return result;
}

@Override
public  boolean equals(Object obj) {
if (this == obj)
return true;
if (obj == null)
return false;
if (getClass() != obj.getClass())
return false;
Product other = (Product) obj;
if (categoryId != other.categoryId)
return false;
if (id != other.id)
return false;
return true;
}

}
