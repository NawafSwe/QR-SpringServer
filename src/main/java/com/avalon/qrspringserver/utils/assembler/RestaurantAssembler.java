package com.avalon.qrspringserver.utils.assembler;

import com.avalon.qrspringserver.model.Restaurant;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class RestaurantAssembler implements RepresentationModelAssembler<Restaurant, EntityModel<Restaurant>> {
    @Override
    public EntityModel<Restaurant> toModel(Restaurant entity) {
        return null;
    }
}
