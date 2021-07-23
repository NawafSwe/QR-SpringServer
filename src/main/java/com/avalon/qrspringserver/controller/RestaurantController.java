package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.error.restaurantErrors.RestaurantDuplicatedEmail;
import com.avalon.qrspringserver.error.restaurantErrors.RestaurantNotFound;
import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import com.avalon.qrspringserver.utils.assembler.RestaurantAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "restaurants")
public class RestaurantController {
    private final RestaurantRepository repository;
    private final RestaurantAssembler assembler;

    public RestaurantController(RestaurantRepository repository, RestaurantAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;

    }

    @GetMapping(path = "")
    public ResponseEntity<?> all() {
        return ResponseEntity
                .ok(repository.findAll().stream().map(assembler::toModel)
                        .collect(Collectors.toList()));
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
        EntityModel<Restaurant> restaurantEntityModel = assembler.toModel(newRestaurant);
        return ResponseEntity.
                created(restaurantEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(restaurantEntityModel);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(HttpServletRequest request, @PathVariable String id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Restaurant findRestaurant = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFound("Restaurant with id: " + id + " Not Found"));

        Restaurant updateRestaurant = mapper.readerForUpdating(findRestaurant).readValue(request.getReader());
        updateRestaurant.setUpdatedAt(new Date());
        EntityModel<Restaurant> restaurantEntityModel = assembler.toModel(repository.save(updateRestaurant));
        return ResponseEntity.ok(restaurantEntityModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        repository.findById(id).orElseThrow(() -> new RestaurantNotFound("Restaurant with id: " + id + " is not found"));
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
