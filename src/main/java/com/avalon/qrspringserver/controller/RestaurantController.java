package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.error.restaurantErrors.RestaurantDuplicatedEmail;
import com.avalon.qrspringserver.error.restaurantErrors.RestaurantNotFound;
import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import com.avalon.qrspringserver.utils.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "restaurants")
public class RestaurantController {
    private final RestaurantRepository repository;


    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "")
    public ResponseEntity<?> all() {
        List<Restaurant> restaurants = repository.findAll();
        ServerResponse<List<Restaurant>> res = new ServerResponse<>(restaurants, HttpStatus.OK);
        return res.sendResponse();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> one(@PathVariable String id) {

        Restaurant foundRestaurant = repository.findById(id).orElseThrow(() -> new RestaurantNotFound("Restaurant with " + id + " Not found"));
        return ResponseEntity.ok(foundRestaurant);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> post(@RequestBody Restaurant body) {
        Restaurant foundRestaurant = repository.findByEmail(body.getEmail());
        if (foundRestaurant != null) {
            throw new RestaurantDuplicatedEmail("this restaurant email is already exits");
        }
        Restaurant newRestaurant = repository.save(body);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newRestaurant);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(HttpServletRequest request, @PathVariable String id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Restaurant findRestaurant = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFound("Restaurant with id: " + id + " Not Found"));

        Restaurant updateRestaurant = mapper.readerForUpdating(findRestaurant).readValue(request.getReader());
        updateRestaurant.setUpdatedAt(new Date());
        Restaurant restaurantEntityModel = repository.save(updateRestaurant);
        return ResponseEntity.ok(restaurantEntityModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        repository.findById(id).orElseThrow(() -> new RestaurantNotFound("Restaurant with id: " + id + " is not found"));
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
