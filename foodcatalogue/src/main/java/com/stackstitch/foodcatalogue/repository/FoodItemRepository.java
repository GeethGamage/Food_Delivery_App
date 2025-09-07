package com.stackstitch.foodcatalogue.repository;

import com.stackstitch.foodcatalogue.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {

    List<FoodItem> findAllByRestaurantId(Integer restaurantId);
}
