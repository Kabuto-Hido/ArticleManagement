package com.example.demo.service;

import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.dto.order.OrderDTO;
import com.example.demo.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order orderCreator(String username, String paymentType, String price);

    OrderDTO changeStatusOrder(long id, String status);

    ListOutputResult getAllOrder(String status, String page, String limit);

    OrderDTO getDetailOrderById(long id);

    ListOutputResult searchOrderForAdmin(SearchRequestDTO requestDTO, String page, String limit);

    ListOutputResult searchOrderForUser(SearchRequestDTO requestDTO, String page, String limit);

    ListOutputResult getOrderByUserId(long userId, String page, String limit);
}
