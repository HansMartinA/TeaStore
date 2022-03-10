package tools.descartes.teastore.recommender.algorithm;

import java.util.List;
import tools.descartes.teastore.entities.Order;
import tools.descartes.teastore.entities.OrderItem;
import tools.descartes.teastore.entities.Product;
import tools.descartes.teastore.entities.User;
public interface IRecommender {
public  void train(List<OrderItem> orderItems, List<Order> orders) ;

public  List<Long> recommendProducts(Long userid, List<OrderItem> currentItems)throws UnsupportedOperationException ;

}
