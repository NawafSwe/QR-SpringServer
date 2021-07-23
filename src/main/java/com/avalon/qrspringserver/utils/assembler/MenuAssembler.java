package com.avalon.qrspringserver.utils.assembler;

import com.avalon.qrspringserver.controller.MenuController;
import com.avalon.qrspringserver.model.Menu;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MenuAssembler implements RepresentationModelAssembler<Menu, EntityModel<Menu>> {
    @Override
    public EntityModel<Menu> toModel(Menu entity) {
        return EntityModel.of(entity, linkTo(methodOn(MenuController.class).one(entity.getId())).withSelfRel());
    }
}
