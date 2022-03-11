package tools.descartes.teastore.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostRemove;
import tools.descartes.teastore.persistence.repository.CacheManager;
import tools.descartes.teastore.entities.OrderItem;
@Entity
public class PersistenceOrderItem extends OrderItem {
@Id
@GeneratedValue
private long id;

private int quantity;

private long unitPriceInCents;

@ManyToOne(optional = false)
private PersistenceProduct product;

@ManyToOne(optional = false)
private PersistenceOrder order;

 PersistenceOrderItem(){
super();
}
@PostRemove
private  void clearCaches() {
CacheManager.MANAGER.clearCache(PersistenceProduct.class);
CacheManager.MANAGER.clearCache(PersistenceOrder.class);
CacheManager.MANAGER.clearRemoteCache(PersistenceOrderItem.class);
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
public  long getProductId() {
return product.getId();
}

@Override
public  void setProductId(long productId) {
}

@Override
public  int getQuantity() {
return quantity;
}

@Override
public  void setQuantity(int quantity) {
this.quantity = quantity;
}

@Override
public  long getUnitPriceInCents() {
return unitPriceInCents;
}

@Override
public  void setUnitPriceInCents(long unitPriceInCents) {
this.unitPriceInCents = unitPriceInCents;
}

public  PersistenceProduct getProduct() {
return product;
}

 void setProduct(PersistenceProduct product) {
this.product = product;
}

@Override
public  long getOrderId() {
return getOrder().getId();
}

public  void setOrderId(long orderId) {
}

public  PersistenceOrder getOrder() {
return order;
}

 void setOrder(PersistenceOrder order) {
this.order = order;
}

}
