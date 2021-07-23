package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.error.restaurantErrors.RestaurantDuplicatedEmail;
import com.avalon.qrspringserver.error.restaurantErrors.RestaurantNotFound;
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

@RestController
@RequestMapping(path = "restaurants")
public class RestaurantController {
    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;

    }

    @GetMapping(path = "")
    ResponseEntity<?> all() throws Exception {
        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping(path = "/{id}")
    ResponseEntity<?> one(@PathVariable String id) {
        Restaurant foundRestaurant = repository.findById(id).orElseThrow(() -> new RestaurantNotFound("Restaurant with " + id + " Not found"));
        return ResponseEntity.ok(foundRestaurant);
    }

    @PostMapping(path = "")
    ResponseEntity<?> post(@RequestBody Restaurant body) throws RestaurantDuplicatedEmail {
        Restaurant foundRestaurant = repository.findByEmail(body.getEmail());
        if (foundRestaurant != null) throw new RestaurantDuplicatedEmail("this restaurant email is already exits");
        Restaurant newRestaurant = repository.save(body);
        return ResponseEntity
                .ok(newRestaurant);
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
