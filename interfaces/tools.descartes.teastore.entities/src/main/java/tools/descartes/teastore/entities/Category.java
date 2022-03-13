package tools.descartes.teastore.entities;

public class Category {
private long id;

private String name;

private String description;

public  Category(){
}
public  Category(Category category){
setId(category.getId());
setName(category.getName());
setDescription(category.getDescription());
}
public  long getId() {
return id;
}

public  void setId(long id) {
this.id = id;
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

@Override
public  int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + (int) (id ^ (id >>> 32));
return result;
}

@Override
public  boolean equals(Object obj) {
if (this == obj)
{
return true;
}
if (obj == null)
{
return false;
}
if (getClass() != obj.getClass())
{
return false;
}
Category other = (Category) obj;
if (id != other.id)
{
return false;
}
return true;
}

}
