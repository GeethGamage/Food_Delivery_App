package com.stackstitch.foodcatalogue.service;

import com.stackstitch.foodcatalogue.dto.FoodCataloguePage;
import com.stackstitch.foodcatalogue.dto.FoodItemDto;
import com.stackstitch.foodcatalogue.dto.Restaurant;
import com.stackstitch.foodcatalogue.entity.FoodItem;
import com.stackstitch.foodcatalogue.mapper.FoodItemMapper;
import com.stackstitch.foodcatalogue.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Autowired
    RestTemplate restTemplate;


    public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
        FoodItem foodItem = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDto));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItem);
    }


    public FoodCataloguePage fetchRestaurantAndFoodCatDetails(Integer restaurantId){
        List<FoodItem> foodItemsList = fetchFoodItemsList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMs(restaurantId);
        return createFoodCataloguePage(foodItemsList, restaurant);

    }

    private List<FoodItem> fetchFoodItemsList(Integer restaurantId){
        return  foodItemRepository.findAllByRestaurantId(restaurantId);

    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMs(Integer restaurantId){
         return restTemplate.getForObject("http://RESTAURANTLISTING/restaurant/fetchById/"+restaurantId, Restaurant.class);

    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemsList, Restaurant restaurant){
       return  new FoodCataloguePage(foodItemsList, restaurant);
    }
}
