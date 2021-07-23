package com.avalon.qrspringserver.utils.assembler;

import com.avalon.qrspringserver.controller.RestaurantController;
import com.avalon.qrspringserver.model.Restaurant;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RestaurantAssembler implements RepresentationModelAssembler<Restaurant, EntityModel<Restaurant>> {
    @Override
    public EntityModel<Restaurant> toModel(Restaurant entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(RestaurantController.class).one(entity.getId()) ).withSelfRel()

        );
    }
}
