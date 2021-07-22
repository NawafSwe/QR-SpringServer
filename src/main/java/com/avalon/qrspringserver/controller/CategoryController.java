package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Category;
import com.avalon.qrspringserver.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "categories")
public class CategoryController {
    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "")
    List<Category> all() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    Optional<Category> one(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping(path = "")
    Category post(@RequestBody Category body) {
        return repository.save(body);
    }

    @PutMapping(path = "/{id}")
    Optional<Category> put(@RequestBody Category body, @PathVariable String id) {
        // find and make sure to update object then return
        return repository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
