package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Category;
import com.avalon.qrspringserver.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    ResponseEntity<?> put(HttpServletRequest request, @PathVariable String id) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Category> findCategory = repository.findById(id);
        try {
            Category updatedCategory = mapper.readerForUpdating(findCategory).readValue(request.getReader());
            repository.save(updatedCategory);
            return ResponseEntity.ok(updatedCategory);
        } catch (IOException error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Internal Server Error")
                            .withDetail("Error while mapping, internal server Error"));
        }

    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
