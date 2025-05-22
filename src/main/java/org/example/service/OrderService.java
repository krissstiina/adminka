package org.example.service;

import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public Map<Integer, Order> getAll() {
        return repo.getOrders();
    }

    public Order getById(int id) {
        return repo.getOrder(id);
    }

    public void save(Order order) {
        repo.getOrders().put(order.getId(), order);
    }

    public void delete(int id) {
        repo.getOrders().remove(id);
    }

    public int getNextId() {
        return repo.getOrders().keySet().stream()
                .max(Integer::compare)
                .orElse(0) + 1;
    }
}