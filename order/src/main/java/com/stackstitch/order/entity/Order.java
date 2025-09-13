package com.stackstitch.order.entity;


import com.stackstitch.order.dto.FoodItemDto;
import com.stackstitch.order.dto.Restaurant;
import com.stackstitch.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    private Integer orderId;
    private List<FoodItemDto> foodItemsList;
    private Restaurant restaurant;
    private UserDTO user;
}
