package tools.descartes.teastore.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostRemove;
import javax.persistence.PreRemove;
import tools.descartes.teastore.persistence.repository.CacheManager;
import tools.descartes.teastore.entities.User;
@Entity
public class PersistenceUser extends User {
@Id
@GeneratedValue
private long id;

private String userName;

private String password;

private String realName;

private String email;

@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
private List<PersistenceOrder> orders;

@PreRemove
private  void deleteOrders() {
EntityManager em = UserRepository.REPOSITORY.getEMF().createEntityManager();
try {
em.getTransaction().begin();
em.createQuery("DELETE FROM PersistenceOrderItem oi WHERE oi.order.user = :user").setParameter("user", this).executeUpdate();
em.createQuery("DELETE FROM PersistenceOrder o WHERE o.user = :user").setParameter("user", this).executeUpdate();
em.getTransaction().commit();
}
finally {
em.close();
}
}

@PostRemove
private  void clearCaches() {
CacheManager.MANAGER.clearCache(PersistenceOrder.class);
CacheManager.MANAGER.clearRemoteCache(PersistenceUser.class);
}

 PersistenceUser(){
super();
orders = new  ArrayList<>();
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
public  String getUserName() {
return userName;
}

@Override
public  void setUserName(String userName) {
this.userName = userName;
}

@Override
public  String getPassword() {
return password;
}

@Override
public  void setPassword(String password) {
this.password = password;
}

@Override
public  String getRealName() {
return realName;
}

@Override
public  void setRealName(String realName) {
this.realName = realName;
}

@Override
public  String getEmail() {
return email;
}

@Override
public  void setEmail(String email) {
this.email = email;
}

public  List<PersistenceOrder> getOrders() {
return orders;
}

}
