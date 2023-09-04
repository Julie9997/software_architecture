package org.julie.architecture.hw10;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    Order getById(int id);
    List<Order> getAll();

    Product getProductById(int productId);
}
