package org.example.model;

import java.util.List;

public class Order {
    private int id;
    private String customerName;
    private List<Flower> flowers;
    private String phoneNumber;

    public Order(String customerName, List<Flower> flowers, int id, String phoneNumber) {
        this.customerName = customerName;
        this.flowers = flowers;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public double getTotalPrice() {
        if (flowers == null || flowers.isEmpty()) {
            return 0.0;
        }
        return flowers.stream().mapToDouble(Flower::getPrice).sum();
    }

    public Order() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
