package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Item;
import com.avalon.qrspringserver.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "items")
public class ItemController {
    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Item> all() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    Optional<Item> one(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping
    Item post(@RequestBody Item body) {
        return repository.save(body);
    }

    @PutMapping(path = "/${id}")
    Item put(@RequestBody Item body, @PathVariable String id) {
        Item findItem = repository.findById(id).orElseThrow();
        // compare and update
        return findItem;
    }

    @DeleteMapping(path = "/${id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
