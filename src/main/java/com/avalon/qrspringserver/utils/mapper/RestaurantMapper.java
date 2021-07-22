package com.avalon.qrspringserver.utils.mapper;

import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.utils.DTO.RestaurantDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")

public interface RestaurantMapper {
    // to ingore nulls
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRestaurantFromDTO(RestaurantDTO dto, @MappingTarget Restaurant restaurant);
}
