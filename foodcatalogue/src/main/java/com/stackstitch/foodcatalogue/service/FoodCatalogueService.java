package com.stackstitch.foodcatalogue.service;

import com.stackstitch.foodcatalogue.dto.FoodItemDto;
import com.stackstitch.foodcatalogue.entity.FoodItem;
import com.stackstitch.foodcatalogue.mapper.FoodItemMapper;
import com.stackstitch.foodcatalogue.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepository foodItemRepository;


    public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
        FoodItem foodItem = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDto));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItem);
    }

}
