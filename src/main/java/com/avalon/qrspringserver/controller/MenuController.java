package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Menu;
import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.MenuRepository;
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
    List<Menu> listAllRestaurantMenus(@PathVariable String id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        return restaurant.getMenus();
    }

    @GetMapping(path = "menus/{id}")
    Optional<Menu> one(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping(path = "restaurants/{id}/menus")
    Menu post(@RequestBody Menu body, @PathVariable String id) {
        // else throw restaurant not found
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        Menu savedMenu = repository.save(body);
        restaurant.getMenus().add(savedMenu);
        restaurantRepository.save(restaurant);
        return savedMenu;
    }

    @PutMapping(path = "menus/{id}")
    ResponseEntity<?> put(HttpServletRequest request, @PathVariable String id) {
        try {
            Menu findMenu = repository.findById(id).orElseThrow();
            ObjectMapper mapper = new ObjectMapper();
            Menu updatedMenu = mapper.readerForUpdating(findMenu).readValue(request.getReader());
            updatedMenu.setUpdatedAt(new Date());
            repository.save(updatedMenu);
            return ResponseEntity
                    .ok(updatedMenu);

        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Internal Server Error")
                            .withDetail("Error while mapping, internal server Error"));
        }
    }

    @DeleteMapping(path = "menus/{id}")
    void delete(@PathVariable String id) {
        // make sure to find
        repository.deleteById(id);
    }
}
