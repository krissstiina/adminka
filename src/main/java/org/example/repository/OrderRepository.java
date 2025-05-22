package org.example.repository;

import org.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    private final Map<Integer, Order> orders = new ConcurrentHashMap<>();

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Integer, Order> cars) {
        this.orders.putAll(cars);
    }

    public Order getOrder(int id) {
        return orders.get(id);
    }
}
