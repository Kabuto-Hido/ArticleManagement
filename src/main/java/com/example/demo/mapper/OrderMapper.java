package com.example.demo.mapper;

import com.example.demo.dto.order.OrderDTO;
import com.example.demo.entity.Order;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDto (Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setPayment(order.getPayment());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setCreatedDate(order.getCreatedDate());
        orderDTO.setModifiedDate(order.getModifiedDate());
        orderDTO.setPayer(UserMapper.toProfileDto(order.getUserId()));
        orderDTO.setOrderDetails(
                order.getOrderDetails().stream()
                        .map(OrderDetailMapper::toDto)
                        .collect(Collectors.toList()));
        return orderDTO;
    }
}
