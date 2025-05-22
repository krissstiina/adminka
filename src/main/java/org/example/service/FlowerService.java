package org.example.service;

import org.example.model.Flower;
import org.example.repository.FlowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FlowerService {
    private final FlowerRepository repo;

    public FlowerService(FlowerRepository repo) {
        this.repo = repo;
    }

    public Map<Integer, Flower> getAll(){
        return repo.getFlowers();
    }

    public Flower getById(int id){
        return repo.getFlower(id);
    }

    public void save(Flower flower){
        repo.getFlowers().put(flower.getId(), flower);
    }

    public void delete(int id){
        repo.getFlowers().remove(id);
    }

    public List<Flower> getByIds(List<Integer> ids) {
        return ids.stream()
                .map(this::getById)
                .filter(flower -> flower != null)
                .toList();
    }
}
