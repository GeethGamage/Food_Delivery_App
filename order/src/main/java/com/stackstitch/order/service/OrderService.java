package com.stackstitch.order.service;

import com.stackstitch.order.dto.OrderDTO;
import com.stackstitch.order.dto.OrderDtoFromFE;
import com.stackstitch.order.dto.UserDTO;
import com.stackstitch.order.entity.Order;
import com.stackstitch.order.mapper.OrderMapper;
import com.stackstitch.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private RestTemplate restTemplate;

    public OrderDTO saveOrder(OrderDtoFromFE orderDetails) {
        Integer newOrderId = sequenceGenerator.generateNextOrderId();
        UserDTO userDto = fetchUserDtoByUserId(orderDetails.getUserId());

        Order order = new Order(newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDto);
        orderRepository.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(order);
    }

    public List<OrderDTO> getOrderById() {
        List<Order> order = orderRepository.findAll();
        return order.stream()
                .map(OrderMapper.INSTANCE::mapOrderToOrderDto).collect(Collectors.toList());
    }

    private UserDTO fetchUserDtoByUserId(Integer userId) {
        return restTemplate.getForObject("http://USERSERVICE/user/getUser/" + userId, UserDTO.class);
    }
}
