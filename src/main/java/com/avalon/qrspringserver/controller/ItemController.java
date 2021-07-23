package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Category;
import com.avalon.qrspringserver.model.Item;
import com.avalon.qrspringserver.repository.CategoryRepository;
import com.avalon.qrspringserver.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    @GetMapping(path = "items/{id}")
    Optional<Item> one(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping(path = "categories/{id}/items")
    ResponseEntity<?> post(@RequestBody Item body, @PathVariable String id) {
        Category findCategory = categoryRepository.findById(id).orElseThrow();
        Item savedItem = repository.save(body);
        findCategory.getItems().add(savedItem);
        categoryRepository.save(findCategory);
        return ResponseEntity.ok("Created");
    }

    @PutMapping(path = "items/{id}")
    ResponseEntity<?> put(HttpServletRequest request, @PathVariable String id) {
        try {
            Item foundItem = repository.findById(id).orElseThrow();
            ObjectMapper mapper = new ObjectMapper();
            Item updatedItem = mapper.readerForUpdating(foundItem).readValue(request.getReader());
            updatedItem.setUpdatedAt(new Date());
            repository.save(updatedItem);
            // compare and update
            return ResponseEntity.ok("Updated");
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Internal Server Error")
                            .withDetail("Error while mapping, internal server Error"));
        }
    }

    @DeleteMapping(path = "items/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
