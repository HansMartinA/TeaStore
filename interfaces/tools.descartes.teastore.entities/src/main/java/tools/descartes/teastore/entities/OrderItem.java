package tools.descartes.teastore.entities;

public class OrderItem {
private long id;

private long productId;

private long orderId;

private int quantity;

private long unitPriceInCents;

public  OrderItem(){
}
public  OrderItem(OrderItem orderItem){
setId(orderItem.getId());
setProductId(orderItem.getProductId());
setOrderId(orderItem.getOrderId());
setQuantity(orderItem.getQuantity());
setUnitPriceInCents(orderItem.getUnitPriceInCents());
}
public  long getId() {
return id;
}

public  void setId(long id) {
this.id = id;
}

public  long getProductId() {
return productId;
}

public  void setProductId(long productId) {
this.productId = productId;
}

public  int getQuantity() {
return quantity;
}

public  void setQuantity(int quantity) {
this.quantity = quantity;
}

public  long getUnitPriceInCents() {
return unitPriceInCents;
}

public  void setUnitPriceInCents(long unitPriceInCents) {
this.unitPriceInCents = unitPriceInCents;
}

public  long getOrderId() {
return orderId;
}

public  void setOrderId(long orderId) {
this.orderId = orderId;
}

@Override
public  int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + (int) (id ^ (id >>> 32));
result = prime * result + (int) (orderId ^ (orderId >>> 32));
result = prime * result + (int) (productId ^ (productId >>> 32));
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
OrderItem other = (OrderItem) obj;
if (id != other.id)
return false;
if (orderId != other.orderId)
return false;
if (productId != other.productId)
return false;
return true;
}

}
