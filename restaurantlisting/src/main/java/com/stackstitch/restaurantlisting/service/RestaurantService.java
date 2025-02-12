package com.stackstitch.restaurantlisting.service;

import com.stackstitch.restaurantlisting.dto.RestaurantDTO;
import com.stackstitch.restaurantlisting.entity.Restaurant;
import com.stackstitch.restaurantlisting.mapper.RestaurantMapper;
import com.stackstitch.restaurantlisting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<RestaurantDTO> findAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDtoList = restaurants.stream()
                .map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDto)
                .collect(Collectors.toList());

        return restaurantDtoList;
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO){
        Restaurant restaurant = restaurantRepository.save(RestaurantMapper.INSTANCE.mapRestaurantDtoToRestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant);
    }

    public ResponseEntity<RestaurantDTO> findByRestaurantId(Integer id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
