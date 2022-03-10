package tools.descartes.teastore.recommender.algorithm;

import java.util.HashMap;
import java.util.Map;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.Product;
import tools.descartes.teastore.entities.User;
public class OrderItemSet {
public  OrderItemSet(){
orderset = new  HashMap<>();
}
private long userId;

private long orderId;

private Map<Long, Integer> orderset;

public  Map<Long, Integer> getOrderset() {
return orderset;
}

public  void setOrderset(Map<Long, Integer> orderset) {
this.orderset = orderset;
}

public  long getOrderId() {
return orderId;
}

public  void setOrderId(long orderId) {
this.orderId = orderId;
}

public  long getUserId() {
return userId;
}

public  void setUserId(long userId) {
this.userId = userId;
}

}
