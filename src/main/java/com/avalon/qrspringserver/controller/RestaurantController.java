package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

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
    Restaurant update(@RequestBody Restaurant body, @PathVariable String id) {
        Restaurant findRestaurant = repository.findById(id)
                .orElseThrow();
        if (body.getEmail() != null) {
            findRestaurant.setEmail(body.getEmail());
        }
        if (body.getName() != null) {
            findRestaurant.setName(body.getName());
        }
        findRestaurant.setUpdatedAt(new Date());
        return repository.save(findRestaurant);

    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
