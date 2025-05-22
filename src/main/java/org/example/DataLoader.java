package org.example;

import org.example.model.Flower;
import org.example.model.Order;
import org.example.repository.FlowerRepository;
import org.example.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final FlowerRepository flowerRepository;
    private final OrderRepository orderRepository;

    public DataLoader(FlowerRepository flowerRepository,
                      OrderRepository  orderRepository) {
        this.flowerRepository = flowerRepository;
        this.orderRepository  = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        HashMap<Integer, Flower> flowers = new HashMap<>();
        flowers.put(1, new Flower("Red",     1, true,  "Rose",        150));
        flowers.put(2, new Flower("Yellow",  2, true,  "Tulip",       100));
        flowers.put(3, new Flower("White",   3, true,  "Lily",        200));
        flowers.put(4, new Flower("Pink",    4, false, "Carnation",   300));
        flowers.put(5, new Flower("Orange",  5, true,  "Gerbera",     350));

        flowerRepository.setFlowers(flowers);

        HashMap<Integer, Order> orders = new HashMap<>();
        orders.put(1, new Order(
                "Alice Johnson",
                List.of(flowers.get(1), flowers.get(2), flowers.get(3)),
                1,
                "+358-555-1111"));

        orders.put(2, new Order(
                "Bob Smith",
                List.of(flowers.get(5), flowers.get(4)),
                2,
                "+358-555-2222"));

        orderRepository.setOrders(orders);

        System.out.println("Initial data loaded successfully!");
    }
}
