package com.avalon.qrspringserver.utils.assembler;

import com.avalon.qrspringserver.model.Category;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CategoryAssembler implements RepresentationModelAssembler<Category, EntityModel<Category>> {
    @Override
    public EntityModel<Category> toModel(Category entity) {
        return null;
    }
}
