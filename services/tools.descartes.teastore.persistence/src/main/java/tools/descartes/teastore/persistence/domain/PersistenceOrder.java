package tools.descartes.teastore.persistence.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostRemove;
import javax.persistence.PreRemove;
import tools.descartes.teastore.persistence.repository.CacheManager;
import tools.descartes.teastore.entities.Order;
@Entity
public class PersistenceOrder extends Order {
@Id
@GeneratedValue
private long id;

private LocalDateTime orderTime;

private long totalPriceInCents;

private String addressName;

private String address1;

private String address2;

private String creditCardCompany;

private String creditCardNumber;

private LocalDate creditCardExpiryLocalDate;

@OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
private List<PersistenceOrderItem> orderItems;

@ManyToOne(optional = false)
private PersistenceUser user;

 PersistenceOrder(){
super();
orderItems = new  ArrayList<PersistenceOrderItem>();
orderTime = LocalDateTime.now();
creditCardExpiryLocalDate = LocalDate.now();
}
@PreRemove
private  void deleteOrders() {
EntityManager em = OrderRepository.REPOSITORY.getEMF().createEntityManager();
try {
em.getTransaction().begin();
em.createQuery("DELETE FROM PersistenceOrderItem oi WHERE oi.order = :order").setParameter("order", this).executeUpdate();
em.getTransaction().commit();
}
finally {
em.close();
}
}

@PostRemove
private  void clearCaches() {
CacheManager.MANAGER.clearCache(PersistenceUser.class);
CacheManager.MANAGER.clearCache(PersistenceOrderItem.class);
CacheManager.MANAGER.clearRemoteCache(PersistenceOrder.class);
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
public  long getUserId() {
return user.getId();
}

@Override
public  void setUserId(long userId) {
}

public  LocalDateTime getOrderTime() {
return orderTime;
}

public  void setOrderTime(LocalDateTime orderTime) {
this.orderTime = orderTime;
}

@Override
public  String getTime() {
return getOrderTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
}

@Override
public  void setTime(String time) {
if (time != null && !time.isEmpty())
{
setOrderTime(LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
}
}

@Override
public  long getTotalPriceInCents() {
return totalPriceInCents;
}

@Override
public  void setTotalPriceInCents(long totalPriceInCents) {
this.totalPriceInCents = totalPriceInCents;
}

@Override
public  String getAddressName() {
return addressName;
}

@Override
public  void setAddressName(String addressName) {
this.addressName = addressName;
}

@Override
public  String getAddress1() {
return address1;
}

@Override
public  void setAddress1(String address1) {
this.address1 = address1;
}

@Override
public  String getAddress2() {
return address2;
}

@Override
public  void setAddress2(String address2) {
this.address2 = address2;
}

@Override
public  String getCreditCardCompany() {
return creditCardCompany;
}

@Override
public  void setCreditCardCompany(String creditCardCompany) {
this.creditCardCompany = creditCardCompany;
}

@Override
public  String getCreditCardNumber() {
return creditCardNumber;
}

@Override
public  void setCreditCardNumber(String creditCardNumber) {
this.creditCardNumber = creditCardNumber;
}

public  LocalDate getCreditCardExpiryLocalDate() {
return creditCardExpiryLocalDate;
}

public  void setCreditCardExpiryLocalDate(LocalDate creditCardExpiryLocalDate) {
this.creditCardExpiryLocalDate = creditCardExpiryLocalDate;
}

@Override
public  String getCreditCardExpiryDate() {
return getCreditCardExpiryLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
}

@Override
public  void setCreditCardExpiryDate(String creditCardExpiryDate) {
if (creditCardExpiryDate != null && !creditCardExpiryDate.isEmpty())
{
setCreditCardExpiryLocalDate(LocalDate.parse(creditCardExpiryDate, DateTimeFormatter.ISO_LOCAL_DATE));
}
}

public  List<PersistenceOrderItem> getOrderItems() {
return orderItems;
}

public  void setOrderItems(List<PersistenceOrderItem> orderItems) {
this.orderItems = orderItems;
}

public  PersistenceUser getUser() {
return user;
}

public  void setUser(PersistenceUser user) {
this.user = user;
}

}
