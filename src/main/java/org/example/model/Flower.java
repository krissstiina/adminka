package org.example.model;

public class Flower {
    private int id;
    private String name;
    private String color;
    private double price;
    private boolean isInStock;

    public Flower(String color, int id, boolean isInStock, String name, double price) {
        this.color = color;
        this.id = id;
        this.isInStock = isInStock;
        this.name = name;
        this.price = price;
    }

    public Flower() {
    }

    public String getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
