package org.example.repository;

import org.example.model.Flower;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FlowerRepository {
    private final Map<Integer, Flower> flowers = new ConcurrentHashMap<>();

    public Map<Integer, Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(HashMap<Integer, Flower> cars) {
        this.flowers.putAll(cars);
    }

    public Flower getFlower(int id) {
        return flowers.get(id);
    }

    public List<Flower> getAllFlowersInStock() {
        return flowers.values()
                .stream()
                .filter(Flower::isInStock)
                .toList();
    }
}

