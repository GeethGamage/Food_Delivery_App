package com.stackstitch.order.controller;

import com.stackstitch.order.dto.OrderDTO;
import com.stackstitch.order.dto.OrderDtoFromFE;
import com.stackstitch.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDtoFromFE order) {
        OrderDTO orderDto = orderService.saveOrder(order);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDTO>> getOrder() {
        List<OrderDTO> orderDto = orderService.getOrderById();
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}
