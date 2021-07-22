package com.avalon.qrspringserver.utils.assembler;

import com.avalon.qrspringserver.model.Menu;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class MenuAssembler implements RepresentationModelAssembler<Menu, EntityModel<Menu>> {
    @Override
    public EntityModel<Menu> toModel(Menu entity) {
        return null;
    }
}
