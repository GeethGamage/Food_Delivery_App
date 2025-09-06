package com.stackstitch.foodcatalogue.mapper;

import com.stackstitch.foodcatalogue.dto.FoodItemDto;
import com.stackstitch.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItemDto mapFoodItemToFoodItemDto(FoodItem foodItem);

    FoodItem mapFoodItemDtoToFoodItem(FoodItemDto foodItemDto);
}
