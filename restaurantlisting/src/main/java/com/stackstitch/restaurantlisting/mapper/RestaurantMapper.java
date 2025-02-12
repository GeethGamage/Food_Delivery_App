package com.stackstitch.restaurantlisting.mapper;

import com.stackstitch.restaurantlisting.dto.RestaurantDTO;
import com.stackstitch.restaurantlisting.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDtoToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO mapRestaurantToRestaurantDto(Restaurant restaurant);
}
