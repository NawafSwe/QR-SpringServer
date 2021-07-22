package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.Menu;
import com.avalon.qrspringserver.repository.MenuRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "menus")
public class MenuController {
    private final MenuRepository repository;

    public MenuController(MenuRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Menu> all() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    Optional<Menu> one(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping
    Menu post(@RequestBody Menu body) {
        return repository.save(body);
    }

    @PutMapping(path = "/{id}")
    Optional<Menu> put(@RequestBody Menu body, @PathVariable String id) {
        Optional<Menu> findMenu = repository.findById(id);
        // compare then save and return
        return findMenu;
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable String id) {
        // make sure to find
        repository.deleteById(id);
    }
}
