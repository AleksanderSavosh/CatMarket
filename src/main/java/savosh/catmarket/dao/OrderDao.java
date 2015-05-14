package savosh.catmarket.dao;

import savosh.catmarket.model.Order;

import java.util.List;
import java.util.Set;

public interface OrderDao extends Crud<Order> {

    public Order createWithOrderProducts(Order order);

    public Set<Order> readAllWithOrderProducts();

}
