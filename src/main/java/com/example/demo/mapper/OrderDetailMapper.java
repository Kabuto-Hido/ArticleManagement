package com.example.demo.mapper;

import com.example.demo.dto.order.OrderDTO;
import com.example.demo.dto.orderDetail.OrderDetailDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;

public class OrderDetailMapper {
    public static OrderDetailDTO toDto (OrderDetail orderDetail){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setDescription(orderDetail.getDescription());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setUnitPrice(orderDetail.getUnitPrice());
        return orderDetailDTO;
    }
}
