package com.example.demo.service.impl;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.SearchCriteria;
import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.dto.order.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FiltersSpecificationServiceImpl<Order> orderFiltersSpecificationService;

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
        if (order.getStatus().equals(status)) {
            throw new IllegalStateException("Order already in status " + status);
        } else {
            order.setStatus(status);
            orderRepository.save(order);
        }
        return OrderMapper.toDto(order);
    }

    @Override
    public ListOutputResult getAllOrder(String status, String page, String limit) {
        Pageable pageable = preparePaging(page, limit);
        Page<Order> orders;
        if (status == null) {
            orders = orderRepository.findAll(pageable);
        } else {
            orders = orderRepository.findAllByStatus(status, pageable);
        }
        return resultPaging(orders);
    }

    @Override
    public OrderDTO getDetailOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Order not exist!!"));
        return OrderMapper.toDto(order);
    }

    @Override
    public ListOutputResult searchOrderForAdmin(SearchRequestDTO requestDTO, String page, String limit) {
        Pageable pageable = preparePaging(page, limit);

        Specification<Order> orderSpecification = orderFiltersSpecificationService.getSearchSpecification(
                requestDTO.getCriteriaList()
        );

        Page<Order> orders = orderRepository.findAll(orderSpecification, pageable);
        return resultPaging(orders);
    }

    @Override
    public ListOutputResult searchOrderForUser(SearchRequestDTO requestDTO, String page, String limit) {
        //check is valid user
        String username = ApplicationUserService.GetUsernameLoggedIn();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot found user with username = " + username));

        Pageable pageable = preparePaging(page, limit);

        SearchCriteria userCriteria = new SearchCriteria(
                "id", String.valueOf(user.getId()),
                SearchCriteria.Operation.JOIN, "user");

        List<SearchCriteria> newCriteria = requestDTO.getCriteriaList();
        newCriteria.add(userCriteria);
        requestDTO.setCriteriaList(newCriteria);

        Specification<Order> orderSpecification = orderFiltersSpecificationService.getSearchSpecification(
                requestDTO.getCriteriaList()
        );

        Page<Order> orders = orderRepository.findAll(orderSpecification, pageable);
        return resultPaging(orders);
    }

    @Override
    public ListOutputResult getOrderByUserId(long userId, String page, String limit) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Can not found user with id = " + userId));

        Pageable pageable = preparePaging(page, limit);

        Page<Order> orders = orderRepository.findAllByUserIdId(userId, pageable);

        return resultPaging(orders);
    }

    private Boolean isNumber(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Boolean isValidNumber(String num) {
        return num != null && !num.isEmpty() && isNumber(num) && Long.parseLong(num) >= 0;
    }

    public Pageable preparePaging(String page, String limit) {
        limit = isValidNumber(limit) ? limit : GlobalVariable.DEFAULT_LIMIT_SEARCH;
        page = isValidNumber(page) ? page : GlobalVariable.DEFAULT_PAGE;
        return PageRequest.of((Integer.parseInt(page) - 1), Integer.parseInt(limit),
                Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    public ListOutputResult resultPaging(Page<Order> orders) {
        List<OrderDTO> postResponseDTOS = new ArrayList<>();
        for (Order order : orders) {
            postResponseDTOS.add(OrderMapper.toDto(order));
        }
        ListOutputResult result = new ListOutputResult(0, 0, null, null, new ArrayList<>());

        if (!orders.isEmpty()) {
            result.setResult(postResponseDTOS);
            result.setTotalPage(orders.getTotalPages());
            result.setItemsNumber(orders.getTotalElements());

            if (orders.hasNext()) {
                result.setNextPage((long) orders.nextPageable().getPageNumber() + 1);
            }
            if (orders.hasPrevious()) {
                result.setPreviousPage((long) orders.previousPageable().getPageNumber() + 1);
            }
        }
        return result;
    }
}
