package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.RestaurantRepository;
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
    ResponseEntity<?> post(@RequestBody Restaurant body) {
        try {
            // creating Advice controller
            // for validating restaurant to throw relative error message
            // Restaurant fnRestaurant = repository.findByEmail(body.getEmail());
            // System.out.println("found rest with id: " + fnRestaurant);
            Restaurant newRestaurant = repository.save(body);
            return ResponseEntity
                    .ok(newRestaurant);
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Internal Server Error")
                            .withDetail("Server cannot handle the request, if problem persist contact the team")
                    );
        }
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<?> update(HttpServletRequest request, @PathVariable String id) {
        ObjectMapper mapper = new ObjectMapper();
        Restaurant findRestaurant = repository.findById(id).orElseThrow();
        try {
            Restaurant updateRestaurant = mapper.readerForUpdating(findRestaurant).readValue(request.getReader());
            updateRestaurant.setUpdatedAt(new Date());
            repository.save(updateRestaurant);
            return ResponseEntity
                    .ok(updateRestaurant);
        } catch (Exception error) {
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
