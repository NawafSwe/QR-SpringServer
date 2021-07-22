package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.SneakyThrows;
import org.apache.coyote.Response;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmlunit.validation.ValidationProblem;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "restaurants")
public class RestaurantController {
    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;

    }

    @GetMapping(path = "")
    List<Restaurant> all() {
        return repository.findAll();
    }


    @GetMapping(path = "/{id}")
    Optional<Restaurant> one(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping(path = "")
    Restaurant post(@RequestBody Restaurant body) {
        return repository.save(body);
    }


    @PutMapping(path = "/{id}")
    ResponseEntity<?> update(HttpServletRequest request, @PathVariable String id) {
        ObjectMapper mapper = new ObjectMapper();
        Restaurant findRestaurant = repository.findById(id)
                .orElseThrow();
        try {
            Restaurant updateRestaurant = mapper.readerForUpdating(findRestaurant).readValue(request.getReader());
            findRestaurant.setUpdatedAt(new Date());
            return ResponseEntity
                    .ok(repository.saveAndFlush(updateRestaurant));
        } catch (IOException error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Internal Server Error")
                            .withDetail("Error while mapping, internal server Error")
                    );
        }
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);

    }
}
