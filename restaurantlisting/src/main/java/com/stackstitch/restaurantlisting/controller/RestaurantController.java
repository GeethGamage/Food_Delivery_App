package com.stackstitch.restaurantlisting.controller;


import com.stackstitch.restaurantlisting.dto.RestaurantDTO;
import com.stackstitch.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
     public ResponseEntity<List<RestaurantDTO>> getAllRestaurants(){
        List<RestaurantDTO> restaurants = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PostMapping("/saveRestaurant")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO restaurant = restaurantService.addRestaurant(restaurantDTO);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Integer id){
        return restaurantService.findByRestaurantId(id);
    }

}
