package com.example.demo.service.impl;

import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.order.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Order orderCreator(String username, String paymentType, String price) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("Cannot found user with username = " + username));

        Order newOrder = new Order(
                GlobalVariable.ORDER_STATUS.PENDING.toString(),
                paymentType,
                Long.parseLong(price),
                user
        );
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(new OrderDetail(
                Long.parseLong(price),
                1L,
                "Upgrade to VIP account",
                newOrder
        ));

        newOrder.setOrderDetails(orderDetailList);
        orderRepository.save(newOrder);

        return newOrder;
    }

    @Override
    public OrderDTO changeStatusOrder(long id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(()
        -> new NotFoundException("Order not exist!!"));
        if(order.getStatus().equals(status)){
            throw new IllegalStateException("Order already in status "+ status);
        }
        else {
            order.setStatus(status);
            orderRepository.save(order);
        }
        return OrderMapper.toDto(order);
    }
}
