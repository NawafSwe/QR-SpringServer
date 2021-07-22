package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.web.bind.annotation.*;

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
    Restaurant update(HttpServletRequest request, @PathVariable String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Restaurant findRestaurant = repository.findById(id)
                .orElseThrow();
        // body.setUpdatedAt(new Date());
        Restaurant updateRestaurant = mapper.readerForUpdating(findRestaurant).readValue(request.getReader());
        repository.saveAndFlush(updateRestaurant);
        return repository.saveAndFlush(updateRestaurant);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);

    }
}
