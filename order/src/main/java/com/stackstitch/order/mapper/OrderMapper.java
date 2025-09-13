package com.stackstitch.order.mapper;

import com.stackstitch.order.dto.OrderDTO;
import com.stackstitch.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO mapOrderToOrderDto(Order order);

    Order mapOrderDtoToOrder(OrderDTO OrderDto);
}
