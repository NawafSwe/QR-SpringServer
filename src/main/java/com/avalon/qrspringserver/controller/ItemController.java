package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Category;
import com.avalon.qrspringserver.model.Item;
import com.avalon.qrspringserver.repository.CategoryRepository;
import com.avalon.qrspringserver.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {
    private final ItemRepository repository;
    private final CategoryRepository categoryRepository;

    public ItemController(ItemRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(path = "categories/{id}/items")
    List<Item> listAllItemsInCategory(@PathVariable String id) {
        Category findCategory = categoryRepository.findById(id).orElseThrow();
        return findCategory.getItems();
    }

    @GetMapping(path = "/{id}")
    Optional<Item> one(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping(path = "")
    Item post(@RequestBody Item body) {
        return repository.save(body);
    }

    @PutMapping(path = "/{id}")
    Item put(@RequestBody Item body, @PathVariable String id) {
        Item findItem = repository.findById(id).orElseThrow();
        // compare and update
        return findItem;
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
