package com.example.demo.controller;

import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "choose one of status : DONE, CANCELED, PAYMENT_CONFIRM, PENDING")
    @GetMapping("/admin/order/get-all")
    public ResponseEntity<?> getAllOrder(@RequestParam(required = false) String status,
                                         @RequestParam(required = false) String page,
                                         @RequestParam(required = false) String limit) {
        return ResponseEntity.ok(orderService.getAllOrder(status, page, limit));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getDetailOrderById(@PathVariable long id){
        return ResponseEntity.ok(orderService.getDetailOrderById(id));
    }

    @GetMapping("/order")
    public ResponseEntity<?> getOrderByUserId(@RequestParam long userId,
                                              @RequestParam(required = false) String page,
                                              @RequestParam(required = false) String limit){
        ListOutputResult listOrder = orderService.getOrderByUserId(userId, page, limit);
        if(listOrder.getItemsNumber() == 0L){
            return new ResponseEntity<>(listOrder, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(listOrder);
    }

    @GetMapping("/admin/order/search")
    public ResponseEntity<?> searchOrderForAdmin(@RequestBody SearchRequestDTO requestDTO,
                                         @RequestParam(required = false) String page,
                                         @RequestParam(required = false) String limit){
        ListOutputResult result = orderService.searchOrderForAdmin(requestDTO,page,limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/order/user/search")
    public ResponseEntity<?> searchOrderForUser(@RequestBody SearchRequestDTO requestDTO,
                                                 @RequestParam(required = false) String page,
                                                 @RequestParam(required = false) String limit){
        ListOutputResult result = orderService.searchOrderForUser(requestDTO,page,limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}