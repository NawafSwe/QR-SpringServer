package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

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
        Restaurant addRestaurant = repository.save(body);
        return addRestaurant;
    }
}
