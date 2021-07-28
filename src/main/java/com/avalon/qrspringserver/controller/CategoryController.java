package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.error.categoryErrors.CategoryNotFound;
import com.avalon.qrspringserver.error.menuErrors.MenuNotFound;
import com.avalon.qrspringserver.model.Category;
import com.avalon.qrspringserver.model.Menu;
import com.avalon.qrspringserver.repository.CategoryRepository;
import com.avalon.qrspringserver.repository.MenuRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class CategoryController {
    private final CategoryRepository repository;
    private final MenuRepository menuRepository;

    public CategoryController(CategoryRepository repository, MenuRepository menuRepository) {
        this.repository = repository;
        this.menuRepository = menuRepository;
    }

    @GetMapping(path = "menus/{id}/categories")
    ResponseEntity<?> listAllMenuCategories(@PathVariable String id) {
        Menu foundMenu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFound("Menu with id: " + id + "Not found"));
        List<Category> categories = foundMenu.getCategories();
        return ResponseEntity
                .ok(categories);
    }

    @GetMapping(path = "categories/{id}")
    ResponseEntity<?> one(@PathVariable String id) {
        Category foundCategory = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category with id: " + id + " was not found"));
        return ResponseEntity.ok(foundCategory);
    }


    @PostMapping(path = "menus/{id}/categories")
    ResponseEntity<?> post(@RequestBody Category body, @PathVariable String id) {
        // else throw menu not found
        Menu findMenu = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category with id: " + id + " Not Found"));
        Category newCategory = repository.save(body);
        findMenu.getCategories().add(newCategory);
        menuRepository.save(findMenu);
        // must be created
        return ResponseEntity.ok("Created");
    }

    @PutMapping(path = "categories/{id}")
    ResponseEntity<?> put(HttpServletRequest request, @PathVariable String id) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Category findCategory = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category with id: " + id + " Not Found"));
        Category updatedCategory = mapper.readerForUpdating(findCategory).readValue(request.getReader());
        Category savedCategory = repository.save(updatedCategory);
        return ResponseEntity.ok(savedCategory);


    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> delete(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
