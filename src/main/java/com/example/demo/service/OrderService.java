package com.example.demo.service;

import com.example.demo.dto.order.OrderDTO;
import com.example.demo.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order orderCreator(String username, String paymentType, String price);
    OrderDTO changeStatusOrder(long id, String status);
}
