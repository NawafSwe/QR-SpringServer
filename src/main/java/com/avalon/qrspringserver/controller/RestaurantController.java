package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
