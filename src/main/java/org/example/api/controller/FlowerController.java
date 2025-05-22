package org.example.api.controller;

import org.example.model.Flower;
import org.example.service.FlowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    private final FlowerService service;

    public FlowerController(FlowerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Flower>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flower> getById(@PathVariable int id) {
        Flower flower = service.getById(id);
        return flower != null ? ResponseEntity.ok(flower)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Flower> create(@RequestBody Flower flower) {
        service.save(flower);
        return ResponseEntity.status(HttpStatus.CREATED).body(flower);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flower> update(@PathVariable int id, @RequestBody Flower updated) {
        if (service.getById(id) != null) {
            updated.setId(id);
            service.save(updated);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.getById(id) != null) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
