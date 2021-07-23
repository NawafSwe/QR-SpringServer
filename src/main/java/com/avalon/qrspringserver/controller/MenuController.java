package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Menu;
import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.MenuRepository;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MenuController {
    private final MenuRepository repository;
    private final RestaurantRepository restaurantRepository;

    public MenuController(MenuRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping(path = "restaurants/{id}/menus")
    List<Menu> all(@PathVariable String id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        return restaurant.getMenus();
    }

    @GetMapping(path = "menus/{id}")
    Optional<Menu> one(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping(path = "restaurants/{id}/menus")
    Menu post(@RequestBody Menu body, @PathVariable String id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        Menu savedMenu = repository.save(body);
        restaurant.getMenus().add(savedMenu);
        restaurantRepository.save(restaurant);
        return savedMenu;
    }

    @PutMapping(path = "menus/{id}")
    Optional<Menu> put(@RequestBody Menu body, @PathVariable String id) {
        Optional<Menu> findMenu = repository.findById(id);
        // compare then save and return
        return findMenu;
    }

    @DeleteMapping(path = "menus/{id}")
    void delete(@PathVariable String id) {
        // make sure to find
        repository.deleteById(id);
    }
}
